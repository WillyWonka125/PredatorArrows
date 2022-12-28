package io.github.zaddyshack.predatorarrows;

import io.github.zaddyshack.predatorarrows.util.DrawDick;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public final class PredatorArrows extends JavaPlugin {

    public static boolean debugMode = true;

    public static EntityType[] targetEntityTypes = {EntityType.ZOMBIE, EntityType.SKELETON, EntityType.CREEPER, EntityType.SPIDER};

    public static Logger log;

    private DrawDick drawDick = new DrawDick();

    @Override
    public void onEnable() {
        // Plugin startup logic
        log = getLogger();
        log.info("PredatorArrows version " + getDescription().getVersion());

        //Register listeners
        //getServer().getPluginManager().registerEvents(new BowListener(), this); //Disabled for now.

        //Register commands
        registerCommands();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerCommands() {
        getCommand("dick").setExecutor(drawDick);

    }
}
