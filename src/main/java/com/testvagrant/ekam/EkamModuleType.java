package com.testvagrant.ekam;

import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.util.IconLoader;
import com.testvagrant.ekam.icons.EkamPluginIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class EkamModuleType extends JavaModuleType {

    private static final String EKAM_MODULE = "EKAM_MODULE";

    public EkamModuleType() {
        super(EKAM_MODULE);
    }

    public static EkamModuleType getInstance() {
        return (EkamModuleType) ModuleTypeManager.getInstance().findByID(EKAM_MODULE);
    }

    @NotNull
    @Override
    public JavaModuleBuilder createModuleBuilder() {
        return new JavaModuleBuilder();
    }

    @NotNull
    @Override
    public String getName() {
        return "Ekam";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Ekam - Unified test automation solution";
    }

    @NotNull
    @Override
    public Icon getNodeIcon(@Deprecated boolean b) {
        return IconLoader.getIcon("/META-INF/pluginIcon_ModuleType.png", EkamPluginIcons.class);
    }

}
