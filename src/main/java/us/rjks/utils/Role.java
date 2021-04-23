package us.rjks.utils;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import us.rjks.discord.Main;
import us.rjks.utils.Data;

import java.io.File;
import java.util.ArrayList;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 23.04.2021 / 17:18
 *
 **************************************************************************/

public class Role {

    private static File logs = new File("plugins/" + Main.getPlugin().getName() + "/", "roles.yml");
    private static YamlConfiguration locscfg = YamlConfiguration.loadConfiguration(logs);

    private static ArrayList<RoleSchem> roles = new ArrayList<>();

    public static void saveRole(String name, ItemStack icon, String description) {
        locscfg.set(name + ".icon", icon);
        locscfg.set(name + ".description", description);
        save();
        roles.add(new RoleSchem(name));
    }

    public static ArrayList<RoleSchem> getKits() {
        return roles;
    }

    public static RoleSchem getRoleFromName(String name) {
        for(RoleSchem kit : getKits()) {
            if(kit.getName().equalsIgnoreCase(name)) return kit;
        }
        return null;
    }

    public static void loadRole() {
        for(String tops : locscfg.getConfigurationSection("").getKeys(false)) {
            System.out.println("[Role] Loaded Role " + tops);
            roles.add(new RoleSchem(tops));
        }
    }

    public static class RoleSchem {

        private String description, name;
        private ItemStack icon;

        public RoleSchem(String name, String description, ItemStack icon) {
            this.name = name;
            this.icon = icon;
        }

        public RoleSchem(String name) {
            this.name = name;
            this.description = locscfg.getString(name + ".description");
            this.icon = (ItemStack) locscfg.get(name + ".icon");
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public ItemStack getIcon() {
            return icon;
        }
    }

    public static boolean create() {
        if(!logs.exists()) {
            try {
                logs.createNewFile();
                saveRole("Goblin", new ItemBuilder(Material.STONE_PICKAXE, "§eGoblins").checkout(), "Your description for Goblins here");
                saveRole("Elves", new ItemBuilder(Material.FEATHER, "§eElves").checkout(), "Your description for Elves here");
                return true;
            }
            catch (Exception localException) {}
        }
        return false;
    }

    private static void save() {
        try { locscfg.save(logs); } catch (Exception e) {}
    }
}
