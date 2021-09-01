package com.testvagrant.ekam.fileTemplate;

import com.intellij.icons.AllIcons;
import com.intellij.ide.fileTemplates.FileTemplateDescriptor;
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptor;
import com.intellij.ide.fileTemplates.FileTemplateGroupDescriptorFactory;
import com.testvagrant.ekam.icons.EkamPluginIcons;

public class EkamFilesTemplateManager implements FileTemplateGroupDescriptorFactory {

    public static final String API_TEST_FILE = "Ekam API Test";
    public static final String WEB_TEST_FILE = "Ekam WEB Test";
    public static final String MOBILE_TEST_FILE = "Ekam MOBILE Test";

    public static final String WEB_PAGE_FILE = "Ekam WEB Page";
    public static final String MOBILE_SCREEN_FILE = "Ekam MOBILE Screen";

    public static final String RETROFIT_CLIENT = "Ekam RETROFIT Client";

    public static final String DATA_CLIENT = "Ekam DATA Client";

    @Override
    public FileTemplateGroupDescriptor getFileTemplatesDescriptor() {
        FileTemplateGroupDescriptor descriptor = new FileTemplateGroupDescriptor("Ekam files", AllIcons.Nodes.Class);
        descriptor.addTemplate(new FileTemplateDescriptor(API_TEST_FILE, AllIcons.Nodes.Class));
        descriptor.addTemplate(new FileTemplateDescriptor(WEB_TEST_FILE, AllIcons.Nodes.Class));
        descriptor.addTemplate(new FileTemplateDescriptor(MOBILE_TEST_FILE, AllIcons.Nodes.Class));
        descriptor.addTemplate(new FileTemplateDescriptor(WEB_PAGE_FILE, AllIcons.Nodes.Class));
        descriptor.addTemplate(new FileTemplateDescriptor(MOBILE_SCREEN_FILE, AllIcons.Nodes.Class));
        descriptor.addTemplate(new FileTemplateDescriptor(RETROFIT_CLIENT, AllIcons.Nodes.Class));
        descriptor.addTemplate(new FileTemplateDescriptor(DATA_CLIENT, AllIcons.Nodes.Class));
        return descriptor;
    }

}
