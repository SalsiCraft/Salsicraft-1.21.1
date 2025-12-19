package net.salsicraft.word;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.salsicraft.Salsicraft;
import net.salsicraft.ores.etherite.EtheriteOre;
import net.salsicraft.ores.shalecite.ShaleciteOre;

import java.util.List;

public class SalsicraftConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_ETHERITE_ORE_BLOCK_KEY = registerKey("etherite_ore_block");
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_SHALECITE_ORE_BLOCK_KEY = registerKey("shalecite_ore_block");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest endReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> endEtheriteOresBlock =
                List.of(OreFeatureConfig.createTarget(endReplaceables, EtheriteOre.ETHERITE_ORE_BLOCK.getDefaultState()));

        List<OreFeatureConfig.Target> endShaleciteOresBlock =
                List.of(OreFeatureConfig.createTarget(endReplaceables, ShaleciteOre.SHALECITE_ORE_BLOCK.getDefaultState()));

        register(context, END_ETHERITE_ORE_BLOCK_KEY, Feature.ORE, new OreFeatureConfig(endEtheriteOresBlock, 9));
        register(context, END_SHALECITE_ORE_BLOCK_KEY, Feature.ORE, new OreFeatureConfig(endShaleciteOresBlock, 9));

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Salsicraft.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
