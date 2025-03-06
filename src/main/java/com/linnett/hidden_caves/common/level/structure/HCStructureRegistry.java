package com.linnett.hidden_caves.common.level.structure;

import com.linnett.hidden_caves.HiddenCaves;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredRegister;


import java.util.function.Supplier;


public class HCStructureRegistry {
    public static final DeferredRegister<StructureType<?>> DEF_REG = DeferredRegister.create(Registries.STRUCTURE_TYPE, HiddenCaves.MODID);

    public static final Supplier<StructureType<UndergroundRiverStructure>> UNDERGROUND_RIVER_STRUCTURE = DEF_REG.register(
            "underground_river", () -> () -> UndergroundRiverStructure.CODEC);

    public static final Supplier<StructureType<LunarCraterStructure>> LUNAR_CRATER_STRUCTURE = DEF_REG.register(
            "lunar_crater", () -> () -> LunarCraterStructure.CODEC);




}