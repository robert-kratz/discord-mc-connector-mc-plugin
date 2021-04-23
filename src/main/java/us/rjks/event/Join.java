package us.rjks.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import us.rjks.discord.Config;
import us.rjks.utils.Data;
import us.rjks.utils.Inventory;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 23.04.2021 / 17:29
 *
 **************************************************************************/

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        System.out.println("DEBUG " + Data.userExists(p.getUniqueId().toString()));
        if (!Data.userExists(p.getUniqueId().toString())) {
            Inventory.openRoleSelect(p);
            p.sendMessage(Config.getMessageAsString("join_message"));
        }
    }

}
