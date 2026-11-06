package com.example.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.network.chat.Component;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.string;

public class PearlLinkClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Keybinds.register();
        ChatHandler.register();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (Keybinds.TRIGGER.consumeClick()) {
                LinkManager.trigger();
            }
        });

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal("pearl")
                .then(literal("link")
                    .then(argument("player", string())
                        .executes(ctx -> {
                            String target = getString(ctx, "player");
                            LinkManager.link(target);
                            ctx.getSource().sendFeedback(Component.literal("§aLinked with " + target));
                            return 1;
                        })))
                .then(literal("unlink")
                    .executes(ctx -> {
                        LinkManager.unlink();
                        ctx.getSource().sendFeedback(Component.literal("§cUnlinked"));
                        return 1;
                    }))
                .then(literal("status")
                    .executes(ctx -> {
                        String linked = LinkManager.getLinkedPlayer();
                        if (linked == null) {
                            ctx.getSource().sendFeedback(Component.literal("§7Not linked to anyone"));
                        } else {
                            ctx.getSource().sendFeedback(Component.literal("§aCurrently linked to: " + linked));
                        }
                        return 1;
                    }))
            );
        });
    }
}
