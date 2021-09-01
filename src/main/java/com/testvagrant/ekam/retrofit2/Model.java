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
        try {
            return new Gson().fromJson(jsonString, Collection.class);
        } catch (Exception e) {
            System.out.println("Json parse failed.");
        }
        return null;
    }

    void generateJavaCode(List<Collection.ItemBean> items, boolean isDynamicHeader) {
        for (Collection.ItemBean item : items) {
            int startOffset = mEditor.getCaretModel().getOffset();
            String header = (isDynamicHeader) ? "" : getStaticHeader(item);
            String annotation = getAnnotation(item);
            String method = getMethod(item, isDynamicHeader);
            WriteCommandAction.runWriteCommandAction(mProject, () -> mEditor.getDocument().insertString(startOffset, header + annotation + method + "\n\n"));
        }
    }

    private String getStaticHeader(Collection.ItemBean item) {
        String result = "";
        if (item.getRequest().getHeader() != null && item.getRequest().getHeader().size() > 0) {
            result = "        @Headers({";
            for (Collection.ItemBean.RequestBean.HeaderBean header : item.getRequest().getHeader()) {
                result += "\"" + header.getKey() + ": " + header.getValue() + "\"";
                if (item.getRequest().getHeader().indexOf(header) != item.getRequest().getHeader().size() - 1)
                    result += ", ";
            }
            result += "})\n";
        }
        return result;
    }

    private String getDynamicHeader(Collection.ItemBean item) {
        String result = "";
        if (item.getRequest().getHeader() != null && item.getRequest().getHeader().size() > 0) {
            for (Collection.ItemBean.RequestBean.HeaderBean headerBean : item.getRequest().getHeader()) {
                result += "@Header(\"" + headerBean.getKey() + "\")" + "String " + headerBean.getKey().replaceAll("[^A-Za-z0-9()\\[\\]]", "") + ", ";
            }
        }
        return result;
    }

    private String getAnnotation(Collection.ItemBean item) {
        String result = "";
        result += "        @" + item.getRequest().getMethod();
        if (item.getRequest().getUrl() instanceof String)
            result += "(\"" + getApiPath((String) item.getRequest().getUrl(), false) + "\")";
        else if (item.getRequest().getUrl() instanceof LinkedTreeMap) {
            LinkedTreeMap url = (LinkedTreeMap) item.getRequest().getUrl();
            result += "(\"" + getApiPath(url.get("raw").toString(), false) + "\")";
        }
        return result + "\n";

    }

    private String getApiPath(String url, boolean hasDomain) {
        URI uri;
        try {
            if (!hasDomain) {
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
        String responseType = item.getName().replaceAll(" ", "").trim();
        String methodName = responseType.substring(0, 1).toLowerCase() + responseType.substring(1);

        String result = "        Call<" + responseType + "Response> " + methodName + "(";
        if (isDynamicHeader) result += getDynamicHeader(item);
        if (item.getRequest().getMethod().equalsIgnoreCase("GET")) result = addQueryParams(item, result);
        else result = addBody(item, result);
        return result;
    }

    private String addBody(Collection.ItemBean item, String result) {

        String responseType = item.getName().replaceAll(" ", "").trim();

        if (item.getRequest().getBody() != null) {
            result += "@Body " + responseType + "Request request";
        }
        return result + ");";
    }

    private String addQueryParams(Collection.ItemBean item, String result) {
        return result+");";
    }
}
