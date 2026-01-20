package net.salsicraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.salsicraft.ores.etherite.EtheriteItems;
import net.salsicraft.ores.etherite.EtheriteOre;
import net.salsicraft.ores.shalecite.ShaleciteItems;
import net.salsicraft.ores.shalecite.ShaleciteOre;

import java.util.concurrent.CompletableFuture;

public class SalsicraftLootTableProvider extends FabricBlockLootTableProvider {

    public SalsicraftLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(EtheriteOre.ETHERITE_BLOCK);
        addDrop(ShaleciteOre.SHALECITE_BLOCK);

        addDrop(ShaleciteOre.SHALECITE_ORE_BLOCK, oreDrops(ShaleciteOre.SHALECITE_ORE_BLOCK, ShaleciteItems.SHALECITE_FRAGMENT));
        addDrop(EtheriteOre.ETHERITE_ORE_BLOCK, oreDrops(EtheriteOre.ETHERITE_ORE_BLOCK, EtheriteItems.ETHERITE_FRAGMENT));

        addDrop(ShaleciteOre.SHALECITE_ORE_BLOCK, multipleOreDrops(ShaleciteOre.SHALECITE_ORE_BLOCK, ShaleciteItems.SHALECITE_FRAGMENT, 1, 3));
        addDrop(EtheriteOre.ETHERITE_ORE_BLOCK, multipleOreDrops(EtheriteOre.ETHERITE_ORE_BLOCK, EtheriteItems.ETHERITE_FRAGMENT, 2, 4));

    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
