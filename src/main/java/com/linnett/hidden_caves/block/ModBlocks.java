package com.linnett.hidden_caves.block;

import com.linnett.hidden_caves.HiddenCaves;
import com.linnett.hidden_caves.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(HiddenCaves.MODID);





    public static final DeferredBlock<Block> CAKE_LAYER = registerBlock("cake_layer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> BUTTER_BLOCK = registerBlock("butter_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> CHOCOLATE_BLOCK = registerBlock("chocolate_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> CHISELED_CHOCOLATE_BLOCK = registerBlock("chiseled_chocolate_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));



    public static final DeferredBlock<Block> RED_FROSTED_CAKE_LAYER = registerBlock("red_frosted_cake_layer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> ORANGE_FROSTED_CAKE_LAYER = registerBlock("orange_frosted_cake_layer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> YELLOW_FROSTED_CAKE_LAYER = registerBlock("yellow_frosted_cake_layer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> LIME_FROSTED_CAKE_LAYER = registerBlock("lime_frosted_cake_layer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> GREEN_FROSTED_CAKE_LAYER = registerBlock("green_frosted_cake_layer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> LIGHT_BLUE_FROSTED_CAKE_LAYER = registerBlock("light_blue_frosted_cake_layer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> CYAN_FROSTED_CAKE_LAYER = registerBlock("cyan_frosted_cake_layer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> BLUE_FROSTED_CAKE_LAYER = registerBlock("blue_frosted_cake_layer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> PURPLE_FROSTED_CAKE_LAYER = registerBlock("purple_frosted_cake_layer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> MAGENTA_FROSTED_CAKE_LAYER = registerBlock("magenta_frosted_cake_layer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> PINK_FROSTED_CAKE_LAYER = registerBlock("pink_frosted_cake_layer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> BROWN_FROSTED_CAKE_LAYER = registerBlock("brown_frosted_cake_layer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> WHITE_FROSTED_CAKE_LAYER = registerBlock("white_frosted_cake_layer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> GRAY_FROSTED_CAKE_LAYER = registerBlock("gray_frosted_cake_layer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> LIGHT_GRAY_FROSTED_CAKE_LAYER = registerBlock("light_gray_frosted_cake_layer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> BLACK_FROSTED_CAKE_LAYER = registerBlock("black_frosted_cake_layer",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));







    public static final List<DeferredBlock<Block>> FROSTED_CAKE_LAYERS = List.of(
            RED_FROSTED_CAKE_LAYER,
            ORANGE_FROSTED_CAKE_LAYER,
            YELLOW_FROSTED_CAKE_LAYER,
            LIME_FROSTED_CAKE_LAYER,
            GREEN_FROSTED_CAKE_LAYER,
            LIGHT_BLUE_FROSTED_CAKE_LAYER,
            CYAN_FROSTED_CAKE_LAYER,
            BLUE_FROSTED_CAKE_LAYER,
            PURPLE_FROSTED_CAKE_LAYER,
            MAGENTA_FROSTED_CAKE_LAYER,
            PINK_FROSTED_CAKE_LAYER,
            BROWN_FROSTED_CAKE_LAYER,
            WHITE_FROSTED_CAKE_LAYER,
            GRAY_FROSTED_CAKE_LAYER,
            LIGHT_GRAY_FROSTED_CAKE_LAYER,
            BLACK_FROSTED_CAKE_LAYER
    );

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
