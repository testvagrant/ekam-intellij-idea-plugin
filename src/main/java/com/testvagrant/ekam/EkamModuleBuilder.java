package com.testvagrant.ekam;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.roots.ModifiableRootModel;
import org.jetbrains.annotations.NotNull;

public class EkamModuleBuilder extends ModuleBuilder {

    @Override
    public void setupRootModel(@NotNull ModifiableRootModel model) {
    }

    @Override
    public EkamModuleType getModuleType() {
        return EkamModuleType.getInstance();
    }


}
