package com.linnett.hidden_caves.common.entity;

import com.linnett.hidden_caves.HiddenCaves;
import com.linnett.hidden_caves.common.entity.gingerbread_entity.GingerBreadEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, HiddenCaves.MODID);
    public static final Supplier<EntityType<GingerBreadEntity>> GINGERBREAD_MAN_ENTITY =
            ENTITY_TYPES.register("gingerbread_man",
                    () -> EntityType.Builder.of(GingerBreadEntity::new, MobCategory.AMBIENT)
                            .sized(0.5f, 1.2f)
                            .build(ResourceLocation.fromNamespaceAndPath(HiddenCaves.MODID, "gingerbread_man").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}