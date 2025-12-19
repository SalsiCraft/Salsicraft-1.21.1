 package net.salsicraft.client;

 import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
 import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
 import net.minecraft.registry.RegistryBuilder;
 import net.minecraft.registry.RegistryKeys;
 import net.salsicraft.datagen.SalsicraftBlockTagProvider;
 import net.salsicraft.datagen.SalsicraftLootTableProvider;
 import net.salsicraft.datagen.SalsicraftModelProvider;
 import net.salsicraft.datagen.SalsicraftRegistryDataGenerator;
 import net.salsicraft.word.SalsicraftConfiguredFeatures;
 import net.salsicraft.word.SalsicraftPlacedFeatures;

 public class SalsicraftDataGenerator implements DataGeneratorEntrypoint {

     @Override
     public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
         FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

         pack.addProvider(SalsicraftBlockTagProvider::new);
         pack.addProvider(SalsicraftLootTableProvider::new);
         pack.addProvider(SalsicraftModelProvider::new);
         pack.addProvider(SalsicraftRegistryDataGenerator::new);
     }

     @Override
     public void buildRegistry(RegistryBuilder registryBuilder) {
         registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, SalsicraftConfiguredFeatures::bootstrap);
         registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, SalsicraftPlacedFeatures::bootstrap);
     }
 }
