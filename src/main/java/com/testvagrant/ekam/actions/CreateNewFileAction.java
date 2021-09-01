package com.testvagrant.ekam.actions;

import com.intellij.icons.AllIcons;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.testvagrant.ekam.fileTemplate.EkamFilesTemplateManager;
import org.jetbrains.annotations.NotNull;

public class CreateNewFileAction extends CreateFileFromTemplateAction implements DumbAware {

    public CreateNewFileAction() {
        super("Ekam Component", "Create new Ekam Component.", AllIcons.Nodes.Class);
    }

    @Override
    protected void buildDialog(@NotNull Project project, @NotNull PsiDirectory psiDirectory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle("Ekam Component")
                .addKind("API Test", AllIcons.Nodes.Class, EkamFilesTemplateManager.API_TEST_FILE)
                .addKind("Web Test", AllIcons.Nodes.Class, EkamFilesTemplateManager.WEB_TEST_FILE)
                .addKind("Mobile Test", AllIcons.Nodes.Class, EkamFilesTemplateManager.MOBILE_TEST_FILE)
                .addKind("Page class - Web", AllIcons.Nodes.Class, EkamFilesTemplateManager.WEB_PAGE_FILE)
                .addKind("Screen class - Mobile", AllIcons.Nodes.Class, EkamFilesTemplateManager.MOBILE_SCREEN_FILE)
                .addKind("Retrofit Client", AllIcons.Nodes.Class, EkamFilesTemplateManager.RETROFIT_CLIENT)
                .addKind("Data Client", AllIcons.Nodes.Class, EkamFilesTemplateManager.DATA_CLIENT);
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
