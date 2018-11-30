package me.rokomilic.battlepetsreborn.versions.v1_12_2;

import net.minecraft.server.v1_12_R1.EntityBat;
import net.minecraft.server.v1_12_R1.GenericAttributes;
import net.minecraft.server.v1_12_R1.World;

public class BatPet extends EntityBat {

    public BatPet(World arg) {
        super(arg);
    }

    public BatPet(World arg, boolean l) {
        super(arg);
        this.navigation = new FlyingNav(this, this.getWorld());
        this.setAsleep(false);
        this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.5);
    }

    @Override
    protected void M() {
        if (this.getBukkitEntity().getLocation().getBlock().getLocation().add(0, -1, 0).getBlock().getType().isSolid())
            this.motY += 0.2f;
    }
}