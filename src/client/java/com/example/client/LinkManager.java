package com.example.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class LinkManager {
    private static String linkedPlayer = null;
    private static final String TRIGGER_MSG = "§k§r§lPEARLLINK_TRIGGER§r";

    public static void link(String player) {
        linkedPlayer = player.toLowerCase().trim();
    }

    public static void unlink() {
        linkedPlayer = null;
    }

    public static String getLinkedPlayer() {
        return linkedPlayer;
    }

    public static void trigger() {
        if (linkedPlayer == null) return;

        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null || player.connection == null) return;

        player.connection.sendCommand("msg " + linkedPlayer + " " + TRIGGER_MSG);
    }

    public static void onReceiveTrigger(String from) {
        if (linkedPlayer == null) return;
        if (!from.equalsIgnoreCase(linkedPlayer)) return;

        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null || mc.hitResult == null) return;

        if (mc.hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult bhr = (BlockHitResult) mc.hitResult;
            if (mc.level != null && mc.level.getBlockState(bhr.getBlockPos()).getBlock() instanceof TrapDoorBlock) {
                mc.options.keyUse.setDown(true);
                mc.execute(() -> mc.options.keyUse.setDown(false));
            }
        }
    }
}
