package net.salsicraft.salsicraft.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;

import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.salsicraft.salsicraft.Salsicraft;

public class SalsicraftItems {

    public static final Item ETHERITE_RAW = registerItem("etherite_raw", new Item(new Item.Settings()));
    public static final Item ETHERITE_FRAGMENT = registerItem("etherite_fragment", new Item(new Item.Settings()));

    public static final Item SHALECITE_RAW = registerItem("shalecite_raw", new Item(new Item.Settings()));
    public static final Item SHALECITE_FRAGMENT = registerItem("shalecite_fragment", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Salsicraft.MOD_ID, name), item);
    }

    public static void registerSalsicraftItems() {
        Salsicraft.LOGGER.info("Registrando itens do Salsicraft!");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(ETHERITE_RAW);
            entries.add(SHALECITE_RAW);
            entries.add(ETHERITE_FRAGMENT);
            entries.add(SHALECITE_FRAGMENT);
        });
    }
}
