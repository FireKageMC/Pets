package me.rokomilic.battlepetsreborn.versions.v1_12_2;

import me.rokomilic.battlepetsreborn.BattlePetsReborn;
import me.rokomilic.battlepetsreborn.MobStats;
import net.minecraft.server.v1_12_R1.EntityEndermite;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class CustomPet extends EntityEndermite {
    ArmorStand stand;
    MobStats stats;

    public CustomPet(World arg0) {
        super(arg0);
    }

    public CustomPet(World world, String type, Location loc, Player p) {
        super(world);
        stats = BattlePetsReborn.statsai.get(type.toLowerCase());
        loc = loc.add(stats.offX, stats.offY - 1.28, stats.offZ);
        stand = loc.getWorld().spawn(loc, ArmorStand.class);

        stand.setHelmet(stats.texture);
        stand.setVisible(false);
        stand.setGravity(false);
        stand.setMetadata("Owner", new FixedMetadataValue(BattlePetsReborn.plugin, p.getUniqueId()));
    }

    public void update() {
        stand.teleport(this.getBukkitEntity().getLocation().add(stats.offX, stats.offY - 1.28, stats.offZ));
    }

    public void removeall() {
        stand.remove();
    }

    public void updatename() {
        stand.setCustomName(this.getBukkitEntity().getCustomName());
        stand.setCustomNameVisible(true);
    }
}
