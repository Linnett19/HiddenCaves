package com.linnett.hidden_caves.common.event;

import com.linnett.hidden_caves.HiddenCaves;
import com.linnett.hidden_caves.common.entity.HCEntities;
import com.linnett.hidden_caves.common.entity.client.GrasshopperModel;
import com.linnett.hidden_caves.common.entity.custom.GrasshopperEntity;
import com.linnett.hidden_caves.common.particle.ParticleRegistry;
import com.linnett.hidden_caves.common.particle.custom.LightDust;
import com.linnett.hidden_caves.common.particle.custom.MoonCaveAmbient;
import com.linnett.hidden_caves.common.particle.custom.MoonDrop;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = HiddenCaves.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleRegistry.MOON_DROP.get(), MoonDrop.Factory::new);
        event.registerSpriteSet(ParticleRegistry.MOON_AMBIENT.get(), MoonCaveAmbient.Factory::new);
        event.registerSpriteSet(ParticleRegistry.LIGHT_DUST.get(), LightDust.Provider::new);
    }
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GrasshopperModel.LAYER_LOCATION, GrasshopperModel::createBodyLayer);

    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(HCEntities.GRASSHOPPER.get(), GrasshopperEntity.createAttributes().build());
    }

}
