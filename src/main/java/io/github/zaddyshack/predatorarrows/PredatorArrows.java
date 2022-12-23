package io.github.zaddyshack.predatorarrows;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class PredatorArrows extends JavaPlugin {



    private Logger log;

    @Override
    public void onEnable() {
        // Plugin startup logic
        log = getLogger();


        log.info("PredatorArrows version " + getDescription().getVersion());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
