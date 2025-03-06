package com.linnett.hidden_caves.common.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;


import java.util.function.Supplier;

public class ParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, "hidden_caves");



    public static final Supplier<SimpleParticleType> MOON_DROP = PARTICLE_TYPES.register(
            "moon_drop",
            () -> new SimpleParticleType(false)
    );


    public static final Supplier<SimpleParticleType> MOON_AMBIENT = PARTICLE_TYPES.register(
            "moon_ambient",
            () -> new SimpleParticleType(false)
    );

    public static final Supplier<SimpleParticleType> LIGHT_DUST = PARTICLE_TYPES.register(
            "light_dust",
            () -> new SimpleParticleType(false)
    );


}