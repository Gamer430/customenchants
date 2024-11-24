package org.kosoc.customenchants.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import org.kosoc.customenchants.handlers.HandleDash;
import org.lwjgl.glfw.GLFW;
;

public class CustomenchantsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyBinding dashKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.customenchants.dash",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_ALT,
                "category.customenchants"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (dashKey.wasPressed() && client.player != null) {
                HandleDash.performDash(client.player);
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && client.world.isClient) {
                HandleDash.updateRechargeTimer(client.player);

            }
        });
    }

}
