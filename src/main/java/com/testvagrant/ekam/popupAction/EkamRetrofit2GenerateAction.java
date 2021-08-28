package com.testvagrant.ekam.popupAction;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.testvagrant.ekam.retrofit2.JsonDialog;
import org.jetbrains.annotations.NotNull;

public class EkamRetrofit2GenerateAction extends AnAction {

    @Override
    public void update(AnActionEvent e) {
        // Using the event, evaluate the context, and enable or disable the action.
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project project = e.getRequiredData(CommonDataKeys.PROJECT);

        JsonDialog jsonDialog = new JsonDialog(project, editor);
        jsonDialog.pack();
        jsonDialog.setTitle("Postman collection");
        jsonDialog.setSize(600, 400);
        jsonDialog.setVisible(true);

    }

}
