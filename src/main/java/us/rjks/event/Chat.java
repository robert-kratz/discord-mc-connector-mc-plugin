package us.rjks.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import us.rjks.discord.Config;
import us.rjks.utils.Data;
import us.rjks.utils.Role;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 23.04.2021 / 18:07
 *
 **************************************************************************/

public class Chat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (!Click.progress.containsKey(event.getPlayer())) return;

        event.setCancelled(true);

        if (event.getMessage().contains("#") && event.getMessage().length() > 7) {
            Player p = event.getPlayer();

            Role.RoleSchem role = Role.getRoleFromName(Click.progress.get(event.getPlayer()));
            Data.registerUser(p, role.getName(), event.getMessage());
            p.sendMessage(Config.getMessageAsString("verify_successful")
                    .replaceAll("%role%", role.getName())
                    .replaceAll("%tag%", event.getMessage())
                    .replaceAll("%username%", p.getName()));
            p.sendMessage(Config.getMessageAsString("verify_successful_description")
                    .replaceAll("%role%", role.getName())
                    .replaceAll("%tag%", event.getMessage())
                    .replaceAll("%username%", p.getName()));

            Click.progress.remove(event.getPlayer());
        } else {
            event.getPlayer().sendMessage(Config.getMessageAsString("connecting_enter_creds"));
        }
    }

}
