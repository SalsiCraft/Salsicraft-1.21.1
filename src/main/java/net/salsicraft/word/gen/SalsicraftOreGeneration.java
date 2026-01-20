package net.salsicraft.word.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import net.salsicraft.word.SalsicraftPlacedFeatures;

public class SalsicraftOreGeneration {
    public static void generateOres() {
        // Example for individual Bioms
        // BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FOREST, BiomeKeys.PLAINS),
        // GenerationStep.Feature.UNDERGROUND_ORES,
        //         SalsicraftPlacedFeatures.END_ETHERITE_ORE_BLOCK_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES,
                SalsicraftPlacedFeatures.END_ETHERITE_ORE_BLOCK_PLACED_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES,
                SalsicraftPlacedFeatures.END_SHALECITE_ORE_BLOCK_PLACED_KEY);
    }
}
