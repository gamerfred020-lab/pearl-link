package com.example.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class Keybinds {
    public static KeyMapping TRIGGER;

    public static void register() {
        TRIGGER = KeyBindingHelper.registerKeyBinding(new KeyMapping(
            "key.pearllink.trigger",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            "key.category.pearllink"
        ));
    }
}
