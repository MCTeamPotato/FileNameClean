package com.teampotato.filenameclean.mixin;

import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public class CleanProcesser implements IMixinConfigPlugin {

    private static final Logger LOGGER = LogManager.getLogger();

    public CleanProcesser() {
        Path mods = FMLPaths.GAMEDIR.get().resolve("mods");
        FMLLoader.getLoadingModList().getModFiles().forEach(modFileInfo -> {
            File file = modFileInfo.getFile().getFilePath().toFile();
            String name = file.getName();
            String newName = null;

            if (name.startsWith("【") && name.contains("】")) {
                newName = name.split("】")[1];
            } else if (name.startsWith("[") && name.contains("]")) {
                newName = name.split("]")[1];
            }

            if (newName != null) {
                boolean result = file.renameTo(new File(mods.resolve(newName).toAbsolutePath().toString()));
                if (!result) {
                    LOGGER.error("Failed to rename " + name + " to " + newName);
                } else {
                    LOGGER.info("Successfully rename " + name + " to " + newName);
                }
            }
        });
    }

    @Override
    public void onLoad(String s) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String s, String s1) {
        return false;
    }

    @Override
    public void acceptTargets(Set<String> set, Set<String> set1) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }

    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }
}
