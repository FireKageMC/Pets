package me.rokomilic.battlepetsreborn.events;

import me.rokomilic.battlepetsreborn.BattlePetsReborn;
import me.rokomilic.battlepetsreborn.Language;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

import java.util.UUID;

public class ArmorStandEvent implements Listener {
    BattlePetsReborn plugin;

    public ArmorStandEvent(BattlePetsReborn plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(PlayerArmorStandManipulateEvent event) {
        LivingEntity pett = event.getRightClicked();
        if (!pett.hasMetadata("Owner")) return;
        event.setCancelled(true);
        UUID owner = UUID.fromString(pett.getMetadata("Owner").get(0).asString());
        Player p = event.getPlayer();
        if (!p.getUniqueId().equals(owner)) {
            p.sendMessage(Language.getMessage("petmenu_failed"));
            return;
        }
        LivingEntity pet = BattlePetsReborn.pets.get(owner);
        BattlePetsReborn.openmenu(p, pet);
    }
}
