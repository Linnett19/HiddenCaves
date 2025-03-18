package com.linnett.hidden_caves.common.entity;

import com.linnett.hidden_caves.HiddenCaves;
import com.linnett.hidden_caves.common.entity.custom.GrasshopperEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class HCEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, HiddenCaves.MODID);

    public static final Supplier<EntityType<GrasshopperEntity>> GRASSHOPPER =
            ENTITY_TYPES.register("grasshopper",
                    () -> EntityType.Builder.<GrasshopperEntity>of(GrasshopperEntity::new, MobCategory.CREATURE)
                            .sized(1.15f, 1.2f)
                            .build("grasshopper")
            );







    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}