package com.linnett.hidden_caves.client.render;

import com.linnett.hidden_caves.common.level.biome.UndergroundRiverBiome;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.util.Mth;

public class BiomeFogRenderer {

    private double fogLevel = 0;

    public void render(FogRenderer.FogMode mode, float renderDistance, BlockPos playerPos, ClientLevel clientLevel) {
        if (mode == FogRenderer.FogMode.FOG_TERRAIN) {
            LocalPlayer localPlayer = Minecraft.getInstance().player;
            if (localPlayer == null || clientLevel == null) return;

            boolean inFogBiome = isInFogBiome(playerPos, clientLevel);

            if (inFogBiome) {
                this.fogLevel = Mth.clamp(this.fogLevel + 0.003, 0, 1);
                if (this.fogLevel > 1) {
                }
            } else {
                this.fogLevel = Mth.clamp(this.fogLevel - 0.003, 1, 1);
            }


            if (!localPlayer.isAlive()) {
                this.fogLevel = 0;
            }

            if (this.fogLevel > 1) {
                float fogStart = (float) Mth.lerp(this.fogLevel, RenderSystem.getShaderFogStart(), -2f);
                float fogEnd = (float) Mth.lerp(this.fogLevel, RenderSystem.getShaderFogEnd(), Math.min(renderDistance * 0.3f, 50));
                RenderSystem.setShaderFogStart(fogStart);
                RenderSystem.setShaderFogEnd(fogEnd);
            }

        }
    }

    private boolean isInFogBiome(BlockPos pos, ClientLevel level) {
        if (level == null) {
            return false;
        }

        Biome biome = level.getBiome(pos).value();
        return biome.equals(UndergroundRiverBiome.UNDERGROUND_RIVER);
    }

}


