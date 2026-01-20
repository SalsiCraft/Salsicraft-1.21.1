package net.salsicraft;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SalsicraftItemGroup {
    
    public static final RegistryKey<ItemGroup> SALSICRAFT_KEY = RegistryKey.of(
        RegistryKeys.ITEM_GROUP,
        Identifier.of(Salsicraft.MOD_ID, "salsicraft")
    );
    
    public static final ItemGroup SALSICRAFT_GROUP = Registry.register(
        Registries.ITEM_GROUP,
        SALSICRAFT_KEY,
        FabricItemGroup.builder()
            .icon(() -> new ItemStack(Items.DIAMOND))
            .displayName(Text.translatable("itemGroup.salsicraft"))
            .build()
    );
    
    public static void initialize() {
        Salsicraft.LOGGER.info("Registrando ItemGroup do Salsicraft");
    }
}
