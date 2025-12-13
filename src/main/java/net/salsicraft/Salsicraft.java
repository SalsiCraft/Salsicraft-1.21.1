package net.salsicraft;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Salsicraft implements ModInitializer {
    public static final String MOD_ID = "salsicraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        SalsicraftRegistry.initialize();
    }
}
