package net.salsicraft.ores.etherite;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.salsicraft.Salsicraft;
import net.salsicraft.SalsicraftItemGroup;

public class EtheriteItems {
    
    public static final Item ETHERITE_ORE = register("etherite_ore", 
        new Item(new Item.Settings()));
    
    public static final Item ETHERITE_FRAGMENT = register("etherite_fragment", 
        new Item(new Item.Settings()));

    private static Item register(String name, Item item) {
        return Registry.register(
            Registries.ITEM, 
            Identifier.of(Salsicraft.MOD_ID, name), 
            item
        );
    }

    public static void initialize() {
        Salsicraft.LOGGER.info("Registrando itens de Etherite");
        
        ItemGroupEvents.modifyEntriesEvent(SalsicraftItemGroup.SALSICRAFT_KEY).register(entries -> {
            entries.add(ETHERITE_ORE);
            entries.add(ETHERITE_FRAGMENT);
        });
    }
}
