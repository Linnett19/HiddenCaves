package com.linnett.hidden_caves.common.item;

import com.linnett.hidden_caves.HiddenCaves;
import com.linnett.hidden_caves.common.block.HCBlockRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HiddenCaves.MODID);

    public static final Supplier<CreativeModeTab> UNDERGROUND_RIVER = CREATIVE_MODE_TAB.register("underground_river",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(HCBlockRegistry.RIVER_SLATE.get()))
                    .title(Component.translatable("creativetab.hidden_caves.underground_river"))
                    .displayItems((itemDisplayParameters, output) -> {


                        output.accept(HCBlockRegistry.RIVER_SLATE);
                        output.accept(HCBlockRegistry.POLISHED_RIVER_SLATE);
                        output.accept(HCBlockRegistry.MARBLE);
                        output.accept(HCBlockRegistry.POLISHED_MARBLE);
                        output.accept(HCBlockRegistry.NACRE);
                        output.accept(HCBlockRegistry.POLISHED_NACRE);

                        output.accept(HCBlockRegistry.RIVER_SLATE_PAWN);
                        output.accept(HCBlockRegistry.RIVER_SLATE_ROOK);
                        output.accept(HCBlockRegistry.RIVER_SLATE_BISHOP);
                        output.accept(HCBlockRegistry.RIVER_SLATE_KNIGHT);
                        output.accept(HCBlockRegistry.RIVER_SLATE_QUEEN);
                        output.accept(HCBlockRegistry.RIVER_SLATE_KING);

                        output.accept(HCBlockRegistry.NACRE_PAWN);
                        output.accept(HCBlockRegistry.NACRE_ROOK);
                        output.accept(HCBlockRegistry.NACRE_BISHOP);
                        output.accept(HCBlockRegistry.NACRE_KNIGHT);
                        output.accept(HCBlockRegistry.NACRE_QUEEN);
                        output.accept(HCBlockRegistry.NACRE_KING);

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
