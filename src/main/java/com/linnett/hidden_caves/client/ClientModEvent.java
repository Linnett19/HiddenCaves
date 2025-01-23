package com.linnett.hidden_caves.client;

import com.linnett.hidden_caves.client.render.BiomeFogRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;

public class ClientModEvent {

    private static final BiomeFogRenderer biomeFogRenderer = new BiomeFogRenderer();

    @SubscribeEvent
    public static void onRenderFog(ViewportEvent.RenderFog event) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.player != null && mc.level != null) {
            BlockPos playerPos = mc.player.blockPosition();

            event.setCanceled(true);

            float renderDistance = mc.options.getEffectiveRenderDistance();
            biomeFogRenderer.render(event.getMode(), renderDistance, playerPos, mc.level);
        }
    }

}
