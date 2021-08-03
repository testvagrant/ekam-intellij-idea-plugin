package com.testvagrant.sdk.module;

import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.util.IconLoader;
import com.testvagrant.sdk.module.icons.EkamPluginIcons;
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
    public EkamModuleBuilder createModuleBuilder() {
        return new EkamModuleBuilder();
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
        return IconLoader.getIcon("/META-INF/pluginIcon2.png", EkamPluginIcons.class);
    }

}
