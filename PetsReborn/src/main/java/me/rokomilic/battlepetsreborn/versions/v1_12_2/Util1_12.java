package me.rokomilic.battlepetsreborn.versions.v1_12_2;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class Util1_12 {

    public static boolean fromItemStack(ItemStack item) {
        if (item == null)
            return true;
        if (item.getType() != Material.MONSTER_EGG)
            return true;

        net.minecraft.server.v1_12_R1.ItemStack stack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tagCompound = stack.getTag();

        if (tagCompound != null) {
            @SuppressWarnings("deprecation")
            EntityType type = EntityType.fromName(tagCompound.getCompound("EntityTag").getString("id"));

            if (type != null) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}