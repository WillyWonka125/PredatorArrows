package io.github.zaddyshack.predatorarrows.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.lang.Math;

public class TargetCone {

    // define the cone parameters
    double x0; // x coordinate of the cone's apex
    double y0; // y coordinate of the cone's apex
    double z0; // z coordinate of the cone's apex
    double r = 10; // radius of the cone at the base
    double h = 25; // height of the cone

    // define the projection vector
    double vx; // x component of the projection vector
    double vy; // y component of the projection vector
    double vz; // z component of the projection vector

    // define the point to test
    double x;
    double y;
    double z;

    // calculate the distance from the point to the projection of the apex onto the projection vector
    double d = Math.sqrt((x-x0)*(x-x0) + (y-y0)*(y-y0) + (z-z0)*(z-z0));
    double t = ((x-x0)*vx + (y-y0)*vy + (z-z0)*vz) / (vx*vx + vy*vy + vz*vz);
    double dx = x0 + t*vx - x;
    double dy = y0 + t*vy - y;
    double dz = z0 + t*vz - z;
    double dp = Math.sqrt(dx*dx + dy*dy + dz*dz);

    // calculate the angle between the point and the projection of the apex onto the projection vector
    double angle = Math.atan2(dp, (z-z0));

    // check if the point is inside the cone
    public boolean isPointInside (Location targ) {
        if (dp * Math.sin(angle) <= r && z <= h) {
            return true;
        } else {
            return false;
        }
    }

    public TargetCone(Location apex, Player shooter, Location target) {
        x0 = apex.getX();
        y0 = apex.getY();
        z0 = apex.getZ();

        Vector los = shooter.getLocation().toVector().subtract(shooter.getTargetBlock(null, 25).getLocation().toVector());
        vx = los.getX();
        vy = los.getY();
        vz = los.getZ();

        x = target.getX();
        y = target.getY();
        z = target.getZ();
    }
}
