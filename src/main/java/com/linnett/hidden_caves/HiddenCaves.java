package com.linnett.hidden_caves;

import com.b04ka.cavelib.deprecated.ExpandedBiomes;
import com.linnett.hidden_caves.common.block.HCBlockRegistry;
import com.linnett.hidden_caves.common.item.ModCreativeTabs;
import com.linnett.hidden_caves.common.item.ModItems;
import com.linnett.hidden_caves.common.level.biome.UndergroundRiverBiome;
import com.linnett.hidden_caves.common.level.structure.HCStructureRegistry;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.dimension.LevelStem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

import static com.linnett.hidden_caves.common.level.biome.UndergroundRiverBiome.UNDERGROUND_RIVER;
import static net.neoforged.neoforge.internal.versions.neoforge.NeoForgeVersion.MOD_ID;


@Mod(HiddenCaves.MODID)
public class HiddenCaves {
    public static final String MODID = "hidden_caves";
    private static final Logger LOGGER = LogUtils.getLogger();

    public HiddenCaves(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        HCStructureRegistry.DEF_REG.register(modEventBus);
        ModCreativeTabs.register(modEventBus);




        ModItems.register(modEventBus);
        HCBlockRegistry.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }


    private void commonSetup(final FMLCommonSetupEvent event){
        UndergroundRiverBiome.init();

    }

    public static void init(){
        ExpandedBiomes.addExpandedBiome(UNDERGROUND_RIVER, LevelStem.OVERWORLD);
    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }


    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {


        }
    }
}
