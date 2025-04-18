package com.linnett.hidden_caves.common.block;

import com.linnett.hidden_caves.HiddenCaves;
import com.linnett.hidden_caves.common.block.custon_blocks.CaveMoss;
import com.linnett.hidden_caves.common.block.custon_blocks.ChessBlock;
import com.linnett.hidden_caves.common.block.custon_blocks.ChestNutStemBlock;
import com.linnett.hidden_caves.common.block.custon_blocks.RiverGrassBlock;
import com.linnett.hidden_caves.common.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class HCBlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(HiddenCaves.MODID);





    public static final DeferredBlock<Block> RIVER_SLATE = registerBlock("river_slate",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()
                    .sound(SoundType.TUFF)));

    public static final DeferredBlock<Block> POLISHED_RIVER_SLATE = registerBlock("polished_river_slate",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()
                    .sound(SoundType.POLISHED_TUFF)));

    public static final DeferredBlock<Block> MARBLE = registerBlock("marble",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()
                    .sound(SoundType.BASALT)));

    public static final DeferredBlock<Block> POLISHED_MARBLE = registerBlock("polished_marble",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()
                    .sound(SoundType.BASALT)));

    public static final DeferredBlock<Block> NACRE = registerBlock("nacre",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()
                    .sound(SoundType.MUD)));

    public static final DeferredBlock<Block> POLISHED_NACRE = registerBlock("polished_nacre",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()
                    .sound(SoundType.MUD_BRICKS)));


    public static final DeferredBlock<Block> NACRE_LAPIS_ORE = registerBlock("nacre_lapis_ore",
            () -> new ChessBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> MARBLE_GOLD_ORE = registerBlock("marble_gold_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()
                    .sound(SoundType.BASALT)));
    
    public static final DeferredBlock<Block> RIVER_GRASS = registerBlock("river_grass",
            () -> new RiverGrassBlock(BlockBehaviour.Properties.of()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)));

    public static final DeferredBlock<Block> CAVE_LILLY_PAD = registerBlock("cave_lily_pad",
            () -> new WaterlilyBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()
                    .sound(SoundType.BASALT)));


    public static final DeferredBlock<Block> LICHEN = registerBlock("lichen",
            () -> new CaveMoss(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()
                    .sound(SoundType.MOSS)));

    public static final DeferredBlock<Block> CHESTNUT_STEM = registerBlock("chestnut_stem",
            () -> new ChestNutStemBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()
                    .sound(SoundType.MOSS)));







    public static final DeferredBlock<Block> MOON_STONE = registerBlock("moon_stone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()
                    .sound(SoundType.MOSS)));

    public static final DeferredBlock<Block> MOON_BLOCK = registerBlock("moon_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()
                    .sound(SoundType.MOSS)));




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
