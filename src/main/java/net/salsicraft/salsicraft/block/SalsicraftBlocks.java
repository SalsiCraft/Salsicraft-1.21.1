package net.salsicraft.salsicraft.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.salsicraft.salsicraft.Salsicraft;

public class SalsicraftBlocks {

    public static final Block ETHERITE_BLOCK = registerBlock("etherite_block",
        new Block(AbstractBlock.Settings.create()
            .strength(4f)
            .requiresTool()
            .sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block SHALECITE_BLOCK = registerBlock("shalecite_block",
        new Block(AbstractBlock.Settings.create()
            .strength(4f)
            .requiresTool()
            .sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block SHALECITE_RAW_BLOCK = registerBlock("shalecite_raw_block",
        new Block(AbstractBlock.Settings.create()
            .strength(4f)
            .requiresTool()
            .sounds(BlockSoundGroup.STONE)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Salsicraft.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Salsicraft.MOD_ID, name),
            new BlockItem(block, new Item.Settings()));
    }

    public static void registerSalsicraftBlocks() {
        Salsicraft.LOGGER.info("Registrando blocos do Salsicraft!");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ETHERITE_BLOCK);
            entries.add(SHALECITE_BLOCK);
            entries.add(SHALECITE_RAW_BLOCK);
        });

    }
    
}
