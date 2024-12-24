package com.linnett.hidden_caves.item;

import com.linnett.hidden_caves.HiddenCaves;
import com.linnett.hidden_caves.block.ModBlocks;
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

    public static final Supplier<CreativeModeTab> DOUGH_TRENCHES = CREATIVE_MODE_TAB.register("dough_trenches",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CHOCOLATE_BAR.get()))
                    .title(Component.translatable("creativetab.hidden_caves.dough_trenches"))
                    .displayItems((itemDisplayParameters, output) -> {


                        output.accept(ModItems.BUTTER_PIECE);
                        output.accept(ModItems.CHOCOLATE_BAR);
                        output.accept(ModBlocks.BUTTER_BLOCK);
                        output.accept(ModBlocks.CAKE_LAYER);
                        output.accept(ModBlocks.CHOCOLATE_BLOCK);
                        output.accept(ModBlocks.CHISELED_CHOCOLATE_BLOCK);
                        ModBlocks.FROSTED_CAKE_LAYERS.forEach(output::accept);


                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
