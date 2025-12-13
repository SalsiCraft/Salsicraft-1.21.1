package net.salsicraft;

import net.salsicraft.ores.etherite.EtheriteItems;
import net.salsicraft.ores.etherite.EtheriteOre;
import net.salsicraft.ores.shalecite.ShaleciteItems;
import net.salsicraft.ores.shalecite.ShaleciteOre;

public class SalsicraftRegistry {
    
    public static void initialize() {
        Salsicraft.LOGGER.info("Inicializando registros do Salsicraft");
        
        EtheriteItems.initialize();
        EtheriteOre.initialize();
        
        ShaleciteItems.initialize();
        ShaleciteOre.initialize();
    }
}
