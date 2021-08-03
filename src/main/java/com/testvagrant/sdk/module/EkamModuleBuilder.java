package com.testvagrant.sdk.module;

import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.openapi.roots.ModifiableRootModel;
import org.jetbrains.annotations.NotNull;

public class EkamModuleBuilder extends JavaModuleBuilder {

    @Override
    public void setupRootModel(@NotNull ModifiableRootModel model) {
    }

    @Override
    public EkamModuleType getModuleType() {
        return EkamModuleType.getInstance();
    }


}
