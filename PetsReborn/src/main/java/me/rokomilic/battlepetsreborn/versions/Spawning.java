package me.rokomilic.battlepetsreborn.versions;

import me.rokomilic.battlepetsreborn.BattlePetsReborn;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.player.PlayerInteractEvent;

public interface Spawning {
    public LivingEntity SpawnCreature(PlayerInteractEvent event, BattlePetsReborn plugin);

    public void update(LivingEntity pet, BattlePetsReborn plugin);

    public void setTarget(LivingEntity pet, LivingEntity target);

    public void returnPet(LivingEntity pet);

    public void nameUpdate(LivingEntity pet);

    public void load();
}
