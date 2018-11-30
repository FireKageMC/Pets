package me.rokomilic.battlepetsreborn.versions.v1_12_2;

import me.rokomilic.battlepetsreborn.versions.Util;
import net.minecraft.server.v1_12_R1.BossBattleServer;
import net.minecraft.server.v1_12_R1.EntityWither;
import net.minecraft.server.v1_12_R1.World;

public class WitherPet extends EntityWither {
    boolean baby;

    public WitherPet(World world) {
        super(world);
    }

    public WitherPet(World world, boolean t) {
        super(world);
        baby = false;
        BossBattleServer bossbar = (BossBattleServer) Util.getPrivateField("bF", EntityWither.class, this);
        bossbar.setVisible(false);
        this.navigation = new FlyingNav(this, this.world);
    }

    @Override
    protected void M() {
        //if (baby)
            //this.l(600); // no idea what this is supposed to be, gonna install 1.9 nms at some point and check it

        if (this.getBukkitEntity().getLocation().getBlock().getLocation().add(0, -1, 0).getBlock().getType().isSolid())
            this.motY += 0.2f;
    }

    public void setBaby(boolean flag) {
        baby = flag;
    }
}
