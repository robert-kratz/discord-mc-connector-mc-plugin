package us.rjks.discord;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import us.rjks.cmd.Unverify;
import us.rjks.cmd.Verify;
import us.rjks.event.Chat;
import us.rjks.event.Click;
import us.rjks.event.Join;
import us.rjks.utils.MySQL;
import us.rjks.utils.Role;

import java.io.File;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 23.04.2021 / 16:35
 *
 **************************************************************************/

public class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;

        loadListeners();

        try {
            if(!new File("plugins/" + getName()).exists()) new File("plugins/" + getName()).mkdirs();

            Role.create();
            Config.create();
            Role.loadRole();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MySQL.connect();
        MySQL.createTable();

        if (!MySQL.isConnected()) {
            Bukkit.getConsoleSender().sendMessage("§c§l=======================================================");
            Bukkit.getConsoleSender().sendMessage("§c§l              PLUGIN HAS BEEN DISABLED!");
            Bukkit.getConsoleSender().sendMessage("§c§l       THE MYSQL CONNECTION IS NOT VALID, PLEASE:");
            Bukkit.getConsoleSender().sendMessage("§c§l             CHECK YOUR DETAILS IN config.yml");
            Bukkit.getConsoleSender().sendMessage("§c§l=======================================================");
        }

        System.out.println("[DISCORD] Plugin successfully started");
        System.out.println("[DISCORD] Author: Salty#0299");
        System.out.println("[DISCORD] Version: 1.0.0");
        System.out.println("[DISCORD] Git: link.rjks.us/github");
        System.out.println("[DISCORD] Support: link.rjks.us/support");
    }

    @Override
    public void onDisable() {
        System.out.println("[DISCORD] Plugin successfully stopped");
        super.onDisable();
    }

    @Override
    public void onLoad() {
        System.out.println("[DISCORD] Plugin successfully loaded");
        super.onLoad();
    }

    public void loadListeners() {
        Bukkit.getPluginManager().registerEvents(new Join(), this);
        Bukkit.getPluginManager().registerEvents(new Chat(), this);
        Bukkit.getPluginManager().registerEvents(new Click(), this);

        getCommand("verify").setExecutor(new Verify());
        getCommand("unverify").setExecutor(new Unverify());
    }

    public static Main getPlugin() {
        return plugin;
    }
}
