package us.rjks.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.rjks.discord.Config;
import us.rjks.event.Click;
import us.rjks.utils.Data;
import us.rjks.utils.Role;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 23.04.2021 / 17:59
 *
 **************************************************************************/

public class Unverify implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (Click.progress.containsKey(p)) return false;

            if (Data.userExists(p.getUniqueId().toString())) {
                if (args.length == 0) {
                    if (Data.userExists(p.getUniqueId().toString())) {
                        Data.unregisterUser(p.getUniqueId().toString());
                        p.sendMessage(Config.getMessageAsString("unverified_success"));
                    } else {
                        p.sendMessage(Config.getMessageAsString("not_registered"));
                    }
                } else {
                    p.sendMessage(Config.getMessageAsString("unverify_syntax_help"));
                }
            } else {
                p.sendMessage(Config.getMessageAsString("already_registered"));
            }
        } else {
            sender.sendMessage(Config.getMessageAsString("has_to_be_a_player"));
        }

        return false;
    }
}