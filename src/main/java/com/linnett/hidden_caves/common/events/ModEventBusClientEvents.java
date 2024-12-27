package com.linnett.hidden_caves.common.events;


import com.linnett.hidden_caves.HiddenCaves;
import com.linnett.hidden_caves.common.entity.ModModelLayers;
import com.linnett.hidden_caves.common.entity.gingerbread_entity.GingerBreadModel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = HiddenCaves.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.GINGERBREAD_MAN, GingerBreadModel::createBodyLayer);
    }


}