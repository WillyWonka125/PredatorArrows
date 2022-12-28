package io.github.zaddyshack.predatorarrows;

import io.github.zaddyshack.predatorarrows.util.TargetCone;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.util.Vector;

import java.lang.annotation.Target;
import java.util.Collection;

public class BowListener implements Listener {

    @EventHandler
    public void onShoot (EntityShootBowEvent e) {

        // Return if player didn't shoot a bow
        if (!(e.getEntity() instanceof Player)) {
            return;
        }

        // Get player and select the mob to target
        Player player = (Player) e.getEntity();
        Location playerLoc = player.getLocation();
        Collection<Entity> entities = player.getNearbyEntities(25, 25, 25);
        Entity targetEntity = null;

        //Find the closest mob
        for (Entity ent : entities) {
            if (targetEntity == null) { targetEntity = ent; }
            else if (isValidTarget(ent, player)) {
                targetEntity = ent;
            } else {
                if (ent.getLocation().distance(playerLoc) < targetEntity.getLocation().distance(playerLoc)) {
                    targetEntity = ent;
                }
            }
        }
        //Fly the arrow to the target
        Arrow arrow = (Arrow) e.getProjectile();
        // Build vector to the target
        Vector vector = targetEntity.getLocation().toVector().subtract(arrow.getLocation().toVector());
        arrow.setVelocity(vector);

        if (PredatorArrows.debugMode) {
            player.sendMessage("Target entity is a " + targetEntity.getType().name());
            player.sendMessage("Target location is " + targetEntity.getLocation());
        }
    }

    public boolean isValidTarget (Entity targ, Player shooter) {

        // Check first if entity is an allowable target
        boolean isTargetable = false;
        for (EntityType et : PredatorArrows.targetEntityTypes) {
            if (targ.getType() == et) {
                isTargetable = true;
            }
        }


        if (!isTargetable) return false;

        //Now check if the entity is within the line of sight
        TargetCone los = new TargetCone(shooter.getEyeLocation(), shooter, targ.getLocation());
        if (los.isPointInside(targ.getLocation())) {
            return true;
        }
        return false;
    }


}
