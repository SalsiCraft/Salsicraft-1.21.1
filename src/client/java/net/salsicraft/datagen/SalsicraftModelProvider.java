package net.salsicraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.salsicraft.ores.etherite.EtheriteItems;
import net.salsicraft.ores.etherite.EtheriteOre;
import net.salsicraft.ores.shalecite.ShaleciteItems;
import net.salsicraft.ores.shalecite.ShaleciteOre;

public class SalsicraftModelProvider extends FabricModelProvider {
    public SalsicraftModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(EtheriteOre.ETHERITE_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(EtheriteOre.ETHERITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ShaleciteOre.SHALECITE_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ShaleciteOre.SHALECITE_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(EtheriteItems.ETHERITE_FRAGMENT, Models.GENERATED);
        itemModelGenerator.register(EtheriteItems.ETHERITE_ORE, Models.GENERATED);
        itemModelGenerator.register(ShaleciteItems.SHALECITE_FRAGMENT, Models.GENERATED);
        itemModelGenerator.register(ShaleciteItems.SHALECITE_ORE, Models.GENERATED);
    }
}
