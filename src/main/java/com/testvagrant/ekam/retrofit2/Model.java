package com.testvagrant.ekam.retrofit2;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class Model {
    private Project mProject;
    private Editor mEditor;

    Model(Project project, Editor editor) {
        mProject = project;
        mEditor = editor;
    }

    Collection parsePostman(String jsonString) {
        try{
            return new Gson().fromJson(jsonString, Collection.class);
        } catch (Exception e){
            System.out.println("Json parse failed.");
        }
        return null;
    }

    void generateJavaCode(List<Collection.ItemBean> items, boolean isDynamicHeader) {
        for(Collection.ItemBean item : items) {
            int startOffset;
            if(items.indexOf(item) == 0) startOffset = mEditor.getDocument().getText().lastIndexOf("{")+1;
            else startOffset = mEditor.getDocument().getText().lastIndexOf(";")+1;
            String header = (isDynamicHeader)? "" : getStaticHeader(item);
            String annotation = getAnnotation(item);
            String method = getMethod(item, isDynamicHeader);
            WriteCommandAction.runWriteCommandAction(mProject, ()-> mEditor.getDocument().insertString(startOffset, "\n\n" +header + annotation + method));
        }
    }

    private String getStaticHeader(Collection.ItemBean item) {
        String result = "";
        if(item.getRequest().getHeader()!=null && item.getRequest().getHeader().size()>0) {
            result = "    @Headers({";
            for(Collection.ItemBean.RequestBean.HeaderBean header : item.getRequest().getHeader()) {
                result += "\"" + header.getKey() + ": " + header.getValue() + "\"";
                if(item.getRequest().getHeader().indexOf(header) != item.getRequest().getHeader().size()-1) result += ", ";
            }
            result += "})\n";
        }
        return result;
    }

    private String getDynamicHeader(Collection.ItemBean item) {
        String result = "";
        if(item.getRequest().getHeader()!=null && item.getRequest().getHeader().size() > 0) {
            for(Collection.ItemBean.RequestBean.HeaderBean headerBean : item.getRequest().getHeader()) {
                result += "@Header(\""+headerBean.getKey()+"\")" + "String " + headerBean.getKey().replaceAll("[^A-Za-z0-9()\\[\\]]", "") + ", ";
            }
        }
        return result;
    }

    private String getAnnotation(Collection.ItemBean item) {
        String result = "";
        if(!item.getRequest().getMethod().equalsIgnoreCase("GET")) result += "    @FormUrlEncoded\n";
        result += "    @" + item.getRequest().getMethod();
        if(item.getRequest().getUrl() instanceof String) result += "(\"" + getApiPath((String)item.getRequest().getUrl(), false) + "\")";
        else if(item.getRequest().getUrl() instanceof LinkedTreeMap) {
            LinkedTreeMap url = (LinkedTreeMap)item.getRequest().getUrl();
            result += "(\"" + getApiPath(url.get("raw").toString(), false) + "\")";
        }
        return result + "\n";

    }

    private String getApiPath(String url, boolean hasDomain) {
        URI uri;
        try {
            if(!hasDomain) {
                uri = new URI(url);
                String domain = uri.getHost();
                url = url.replace("http://", "");
                url = url.replace("https://", "");
                url = url.replace(domain, "");
                return url;
            }
            return url;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return url;
    }

    private String getMethod(Collection.ItemBean item, boolean isDynamicHeader) {
        String methodName = item.getName().replaceAll(" ", "").trim();
        String result = "    Call<" + methodName + "Response>> " + methodName + "(";
        if(isDynamicHeader) result += getDynamicHeader(item);
        if(item.getRequest().getMethod().equalsIgnoreCase("GET")) result = addQueryParams(item, result);
        else result = addFieldParams(item, result);
        return result;
    }

    private String addFieldParams(Collection.ItemBean item, String result) {
        //from Url-encoded
        if(item.getRequest().getBody().getUrlencoded()!=null) {
            for (Collection.ItemBean.RequestBean.BodyBean.UrlencodedBean urlencoded : item.getRequest().getBody().getUrlencoded()) {
                result += "@Field(\"" + urlencoded.getKey() + "\") " + "String " + urlencoded.getKey();
                if (item.getRequest().getBody().getUrlencoded().indexOf(urlencoded) != item.getRequest().getBody().getUrlencoded().size() - 1)
                    result += ", ";
            }
            return result + ");";
        }
        //from form-data
        if(item.getRequest().getBody().getFormdata()!=null) {
            for (Collection.ItemBean.RequestBean.BodyBean.FormdataBean formdata : item.getRequest().getBody().getFormdata()) {
                result += "@Field(\"" + formdata.getKey() + "\") " + "String " + formdata.getKey();
                if (item.getRequest().getBody().getFormdata().indexOf(formdata) != item.getRequest().getBody().getFormdata().size() - 1)
                    result += ", ";
            }
            return result + ");";
        }
        return result+");";
    }

    private String addQueryParams(Collection.ItemBean item, String result) {
        return result+");";
    }
}
