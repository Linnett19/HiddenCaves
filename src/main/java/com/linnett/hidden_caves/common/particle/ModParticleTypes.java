package com.linnett.hidden_caves.common.particle;

import com.linnett.hidden_caves.HiddenCaves;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;


public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, HiddenCaves.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MOON_DROP = REGISTRY.register("moon_drop", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MOON_AMBIENT = REGISTRY.register("moon_ambient", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LIGHT_DUST = REGISTRY.register("light_dust", () -> new SimpleParticleType(false));


}
