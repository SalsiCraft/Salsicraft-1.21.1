package net.salsicraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.salsicraft.ores.etherite.EtheriteOre;
import net.salsicraft.ores.shalecite.ShaleciteOre;

import java.util.concurrent.CompletableFuture;

public class SalsicraftBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public SalsicraftBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(EtheriteOre.ETHERITE_BLOCK)
                .add(EtheriteOre.ETHERITE_ORE_BLOCK)
                .add(ShaleciteOre.SHALECITE_BLOCK)
                .add(ShaleciteOre.SHALECITE_ORE_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(EtheriteOre.ETHERITE_BLOCK)
                .add(EtheriteOre.ETHERITE_ORE_BLOCK)
                .add(ShaleciteOre.SHALECITE_BLOCK)
                .add(ShaleciteOre.SHALECITE_ORE_BLOCK);
    }
}
