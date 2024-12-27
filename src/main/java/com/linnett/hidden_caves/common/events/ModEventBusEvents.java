package com.linnett.hidden_caves.common.events;


import com.linnett.hidden_caves.HiddenCaves;
import com.linnett.hidden_caves.common.entity.ModEntities;
import com.linnett.hidden_caves.common.entity.gingerbread_entity.GingerBreadEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = HiddenCaves.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GINGERBREAD_MAN_ENTITY.get(), GingerBreadEntity.createAttributes().build());
    }
}