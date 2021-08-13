package com.testvagrant.ekam.fileTemplate;

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

    public static final String DATA_CLIENT = "Ekam DATA Client";

    @Override
    public FileTemplateGroupDescriptor getFileTemplatesDescriptor() {
        FileTemplateGroupDescriptor descriptor = new FileTemplateGroupDescriptor("Ekam files", EkamPluginIcons.ProjectType);
        descriptor.addTemplate(new FileTemplateDescriptor(API_TEST_FILE, EkamPluginIcons.ProjectType));
        descriptor.addTemplate(new FileTemplateDescriptor(WEB_TEST_FILE, EkamPluginIcons.ProjectType));
        descriptor.addTemplate(new FileTemplateDescriptor(MOBILE_TEST_FILE, EkamPluginIcons.ProjectType));
        descriptor.addTemplate(new FileTemplateDescriptor(WEB_PAGE_FILE, EkamPluginIcons.ProjectType));
        descriptor.addTemplate(new FileTemplateDescriptor(MOBILE_SCREEN_FILE, EkamPluginIcons.ProjectType));
        descriptor.addTemplate(new FileTemplateDescriptor(DATA_CLIENT, EkamPluginIcons.ProjectType));
        return descriptor;
    }

}
