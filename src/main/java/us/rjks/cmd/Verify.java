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
 *  Erstellt: 23.04.2021 / 17:11
 *
 **************************************************************************/

public class Verify implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        System.out.println("debug");
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (Click.progress.containsKey(p)) return false;
            if (!Data.userExists(p.getUniqueId().toString())) {
                if (args.length == 2) {
                    if (Role.getRoleFromName(args[0]) != null) {
                        Role.RoleSchem role = Role.getRoleFromName(args[0]);
                        Data.registerUser(p, role.getName(), args[1]);
                        p.sendMessage(Config.getMessageAsString("verify_successful")
                                .replaceAll("%role%", role.getName())
                                .replaceAll("%tag%", args[1])
                                .replaceAll("%username%", p.getName()));
                        p.sendMessage(Config.getMessageAsString("verify_successful_description")
                                .replaceAll("%role%", role.getName())
                                .replaceAll("%tag%", args[1])
                                .replaceAll("%username%", p.getName()));
                    } else {
                        p.sendMessage(Config.getMessageAsString("role_does_not_exists").replaceAll("%role%", args[0]));
                    }
                } else {
                    p.sendMessage(Config.getMessageAsString("verify_syntax_help"));
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

