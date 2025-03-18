package com.linnett.hidden_caves.mixin.client;

import com.linnett.hidden_caves.client.misc.BiomeMusic;
import com.linnett.hidden_caves.client.misc.BiomeMusicEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.Music;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow
    @Nullable
    public LocalPlayer player;

    @Shadow
    @Nullable
    public ClientLevel level;

    @Inject(method = "getSituationalMusic", at = @At("HEAD"), cancellable = true)
    public void onGetSituationalMusic(CallbackInfoReturnable<Music> cir) {
        if (this.player != null && this.level != null) {
            BiomeMusicEntry entry = BiomeMusic.getInstance().getMusicForBiome(this.level.getBiome(this.player.blockPosition()));
            if (entry != null && entry.shouldPlay()) {
                cir.setReturnValue(entry.getMusic());
            }
        }
    }
}
