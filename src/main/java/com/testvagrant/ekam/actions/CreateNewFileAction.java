package com.testvagrant.ekam.actions;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.testvagrant.ekam.fileTemplate.EkamFilesTemplateManager;
import com.testvagrant.ekam.icons.EkamPluginIcons;
import org.jetbrains.annotations.NotNull;

public class CreateNewFileAction extends CreateFileFromTemplateAction implements DumbAware {

    public CreateNewFileAction() {
        super("Ekam Component", "Create new Ekam API Component.", EkamPluginIcons.ProjectType);
    }

    @Override
    protected void buildDialog(@NotNull Project project, @NotNull PsiDirectory psiDirectory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle("Ekam Component")
                .addKind("API Test", EkamPluginIcons.ProjectType, EkamFilesTemplateManager.API_TEST_FILE)
                .addKind("Web Test", EkamPluginIcons.ProjectType, EkamFilesTemplateManager.WEB_TEST_FILE)
                .addKind("Mobile Test", EkamPluginIcons.ProjectType, EkamFilesTemplateManager.MOBILE_TEST_FILE);
    }

    @Override
    protected String getActionName(PsiDirectory directory, @NotNull String newName, String templateName) {
        return "Create " + newName;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CreateNewFileAction;
    }
}
