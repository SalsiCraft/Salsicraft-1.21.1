 package net.salsicraft.client;

 import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
 import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
 import net.salsicraft.datagen.SalsicraftBlockTagProvider;
 import net.salsicraft.datagen.SalsicraftLootTableProvider;
 import net.salsicraft.datagen.SalsicraftModelProvider;

 public class SalsicraftDataGenerator implements DataGeneratorEntrypoint {

     @Override
     public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
         FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

         pack.addProvider(SalsicraftBlockTagProvider::new);
         pack.addProvider(SalsicraftLootTableProvider::new);
         pack.addProvider(SalsicraftModelProvider::new);
     }
 }
