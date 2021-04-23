package us.rjks.event;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import us.rjks.discord.Config;
import us.rjks.utils.Role;

import java.util.ArrayList;
import java.util.HashMap;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 23.04.2021 / 18:08
 *
 **************************************************************************/

public class Click implements Listener {

    public static HashMap<Player, String> progress = new HashMap<>();

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if ( event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getType() == null) return;

        if (event.getView().getTitle().equalsIgnoreCase(Config.getInventory("role_select_inv_name"))) {
            event.setCancelled(true);
            Player player = ((Player) event.getWhoClicked());
            try {
                String name = "";
                for (Role.RoleSchem role : Role.getKits()) {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().contains(role.getName())) {
                        name = role.getName();
                    }
                }
                if (!name.isEmpty()) {
                    if (!progress.containsKey(player)) {
                        progress.put(player, name);
                    }
                    player.sendMessage(Config.getMessageAsString("connecting_enter_creds"));
                    player.closeInventory();
                    player.playSound(event.getWhoClicked().getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 20L, 20L);
                }
            } catch (Exception e) {

            }
        }
    }
}