package com.linnett.hidden_caves.common.event;

import com.linnett.hidden_caves.HiddenCaves;
import com.linnett.hidden_caves.common.particle.ParticleRegistry;
import com.linnett.hidden_caves.common.particle.custom.LightDust;
import com.linnett.hidden_caves.common.particle.custom.MoonCaveAmbient;
import com.linnett.hidden_caves.common.particle.custom.MoonDrop;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = HiddenCaves.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleRegistry.MOON_DROP.get(), MoonDrop.Factory::new);
        event.registerSpriteSet(ParticleRegistry.MOON_AMBIENT.get(), MoonCaveAmbient.Factory::new);
        event.registerSpriteSet(ParticleRegistry.LIGHT_DUST.get(), LightDust.Provider::new);

    }
}
