package net.salsicraft.ores.etherite;

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
import net.salsicraft.Salsicraft;
import net.salsicraft.SalsicraftItemGroup;

public class EtheriteOre {
    
    public static final Block ETHERITE_BLOCK = register("etherite_block",
        new Block(AbstractBlock.Settings.create()
            .strength(4f)
            .requiresTool()
            .sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block ETHERITE_ORE_BLOCK = register("etherite_ore_block",
        new Block(AbstractBlock.Settings.create()
            .strength(4f)
            .requiresTool()
            .sounds(BlockSoundGroup.STONE)));

    private static Block register(String name, Block block) {
        Block registeredBlock = Registry.register(
            Registries.BLOCK, 
            Identifier.of(Salsicraft.MOD_ID, name), 
            block
        );
        
        Registry.register(
            Registries.ITEM, 
            Identifier.of(Salsicraft.MOD_ID, name),
            new BlockItem(registeredBlock, new Item.Settings())
        );
        
        return registeredBlock;
    }

    public static void initialize() {
        Salsicraft.LOGGER.info("Registrando blocos de Etherite");
        
        ItemGroupEvents.modifyEntriesEvent(SalsicraftItemGroup.SALSICRAFT_KEY).register(entries -> {
            entries.add(ETHERITE_BLOCK);
            entries.add(ETHERITE_ORE_BLOCK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(ETHERITE_ORE_BLOCK);
        });
    }
}
