package net.salsicraft.ores.shalecite;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.salsicraft.Salsicraft;

public class ShaleciteItems {
    
    public static final Item SHALECITE_ORE = register("shalecite_ore", 
        new Item(new Item.Settings()));
    
    public static final Item SHALECITE_FRAGMENT = register("shalecite_fragment", 
        new Item(new Item.Settings()));

    private static Item register(String name, Item item) {
        return Registry.register(
            Registries.ITEM, 
            Identifier.of(Salsicraft.MOD_ID, name), 
            item
        );
    }

    public static void initialize() {
        Salsicraft.LOGGER.info("Registrando itens de Shalecite");
        
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(SHALECITE_ORE);
            entries.add(SHALECITE_FRAGMENT);
        });
    }
}
