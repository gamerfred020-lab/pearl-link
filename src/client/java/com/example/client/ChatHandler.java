package com.example.client;

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;

public class ChatHandler {
    private static final String TRIGGER = "PEARLLINK_TRIGGER";

    public static void register() {
        ClientReceiveMessageEvents.ALLOW_CHAT.register((message, overlay) -> {
            String text = message.getString();

            if (text.contains(TRIGGER)) {
                String from = extractSender(text);
                if (from != null) {
                    LinkManager.onReceiveTrigger(from);
                }
                return false;
            }
            return true;
        });
    }

    private static String extractSender(String text) {
        if (text.contains(" whispers to you:")) {
            return text.split(" whispers to you:")[0].trim();
        }
        if (text.contains(" whispers:")) {
            return text.split(" whispers:")[0].trim();
        }
        if (text.contains(" -> you:")) {
            return text.split(" -> you:")[0].trim();
        }
        if (text.startsWith("<") && text.contains(">")) {
            return text.substring(1, text.indexOf('>')).trim();
        }
        return null;
    }
}
