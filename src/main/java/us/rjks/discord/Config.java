package us.rjks.discord;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.rjks.utils.ItemBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 18.04.2021 / 19:52
 *
 **************************************************************************/

public class Config {

    private static File logs = new File("plugins/" + Main.getPlugin().getName() + "/", "config.yml");
    private static YamlConfiguration locscfg = YamlConfiguration.loadConfiguration(logs);

    private static HashMap<String, Object> cache = new HashMap<>();

    public static Object getProperty(String key) {
        if (cache.containsKey(key)) return cache.get(key);
        if (locscfg.get(key) == null) return null;
        Object element = locscfg.get(key);
        cache.put(key, element);
        return element;
    }

    public static String getMessageAsString(String key) {
        if (cache.containsKey("msg." + key)) return transformMessage(cache.get("msg." + key).toString());
        if (locscfg.get("msg." + key) == null) {
            Bukkit.getConsoleSender().sendMessage("§cATTENTION: MAJOR ERROR WHILE COMPILING CONFIG.YML! COULD NOT FIND: " + "msg." + key);
            return null;
        }
        Object element = locscfg.get("msg." + key);
        cache.put("msg." + key, element);
        return transformMessage(element.toString());
    }

    public static Object getConfig(String key) {
        if (cache.containsKey("config." + key)) return cache.get("config." + key);
        if (locscfg.get("config." + key) == null) {
            Bukkit.getConsoleSender().sendMessage("§cATTENTION: MAJOR ERROR WHILE COMPILING CONFIG.YML! COULD NOT FIND: " + "config." + key);
            return null;
        }
        Object element = locscfg.get("config." + key);
        cache.put("config." + key, element);
        return element;
    }

    public static ItemStack getItemStack(String key) {
        if (cache.containsKey("item." + key)) {
            return (ItemStack) cache.get("item." + key);
        }
        if (locscfg.get("item." + key) == null) {
            Bukkit.getConsoleSender().sendMessage("§cATTENTION: MAJOR ERROR WHILE COMPILING CONFIG.YML! COULD NOT FIND: " + "item." + key);
            return null;
        }
        ItemStack element = (ItemStack) locscfg.get("item." + key);
        ItemMeta meta = element.getItemMeta();
        String display = transformMessage(meta.getDisplayName());
        meta.setDisplayName(display);
        if (meta.getLore() != null) {
            ArrayList<String> lore = new ArrayList<>();
            for (String lor : meta.getLore()) {
                lore.add(transformMessage(lor));
            }
            meta.setLore(lore);
        }
        element.setItemMeta(meta);
        cache.put("item." + key, element);
        return element;
    }

    public static Object getItem(String key) {
        if (cache.containsKey("item." + key)) {
            return cache.get("item." + key);
        }
        if (locscfg.get("item." + key) == null) {
            Bukkit.getConsoleSender().sendMessage("§cATTENTION: MAJOR ERROR WHILE COMPILING CONFIG.YML! COULD NOT FIND: " + "item." + key);
            return null;
        }
        Object element = locscfg.get("item." + key);
        cache.put("item." + key, element);
        return element;
    }

    public static String getInventory(String key) {
        if (cache.containsKey("inv." + key)) {
            return (String) cache.get("inv." + key);
        }
        if (locscfg.get("inv." + key) == null) {
            Bukkit.getConsoleSender().sendMessage("§cATTENTION: MAJOR ERROR WHILE COMPILING CONFIG.YML! COULD NOT FIND: " + "inv." + key);
            return null;
        }
        Object element = transformMessage((String) locscfg.get("inv." + key));
        cache.put("inv." + key, element);
        return element.toString();
    }

    public static String getConfigeAsString(String key) {
        if (cache.containsKey("config." + key)){
            return cache.get("config." + key).toString();
        }
        if (locscfg.get("config." + key) == null) {
            Bukkit.getConsoleSender().sendMessage("§cATTENTION: MAJOR ERROR WHILE COMPILING CONFIG.YML! COULD NOT FIND: " + "config." + key);
            return null;
        }
        String element = transformMessage((String) locscfg.get("config." + key));
        cache.put("config." + key, element);
        return element;
    }

    public static String getConfigAsString(String key) {
        if (cache.containsKey("config." + key)){
            return cache.get("config." + key).toString();
        }
        if (locscfg.get("config." + key) == null) {
            Bukkit.getConsoleSender().sendMessage("§cATTENTION: MAJOR ERROR WHILE COMPILING CONFIG.YML! COULD NOT FIND: " + "config." + key);
            return null;
        }
        String element = transformMessage((String) locscfg.get("config." + key));
        cache.put("config." + key, element);
        return element;
    }

    public static String getMySQLAsString(String key) {
        if (cache.containsKey("mysql." + key)){
            return cache.get("mysql." + key).toString();
        }
        if (locscfg.get("mysql." + key) == null) {
            Bukkit.getConsoleSender().sendMessage("§cATTENTION: MAJOR ERROR WHILE COMPILING CONFIG.YML! COULD NOT FIND: " + "mysql." + key);
            return null;
        }
        String element = transformMessage((String) locscfg.get("mysql." + key));
        cache.put("mysql." + key, element);
        return element;
    }

    public static String getScoreBoardAsString(String key) {
        if (cache.containsKey("scoreboard." + key)){
            return cache.get("scoreboard." + key).toString();
        }
        if (locscfg.get("scoreboard." + key) == null) {
            Bukkit.getConsoleSender().sendMessage("§cATTENTION: MAJOR ERROR WHILE COMPILING CONFIG.YML! COULD NOT FIND: " + "scoreboard." + key);
            return null;
        }
        String element = transformMessage((String) locscfg.get("scoreboard." + key));
        cache.put("scoreboard." + key, element);
        return element;
    }

    public static void setProperty(String key, Object value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        }
        if (value instanceof String) {
            cache.put(key, transformMessage(value.toString()));
        } else if (value instanceof ItemStack){
            ItemStack element = (ItemStack) value;
            ItemMeta meta = element.getItemMeta();
            String display = transformMessage(meta.getDisplayName());
            meta.setDisplayName(display);
            if (meta.getLore() != null) {
                ArrayList<String> lore = new ArrayList<>();
                for (String lor : meta.getLore()) {
                    lore.add(transformMessage(lor));
                }
                meta.setLore(lore);
            }
            element.setItemMeta(meta);
            cache.put(key, element);
        }
        locscfg.set(key, value);
        save();
    }

    public static void loadDefaultConfig() {
        String config = "config.", message = "msg.", item = "item.", inventory = "inv.", scoreboard = "scoreboard.", mysql = "mysql.";

        setProperty(config + "user_unverify","permission.unverify");

        setProperty(mysql + "username", "root");
        setProperty(mysql + "password", "");
        setProperty(mysql + "database", "discord");
        setProperty(mysql + "host", "");
        setProperty(mysql + "port", "3306");

        setProperty(message + "has_to_be_a_player", "&cYou have to be a player to execute this command");
        setProperty(message + "already_registered", "&cYou are already registered");
        setProperty(message + "not_registered", "&cYou are not registered");
        setProperty(message + "role_does_not_exists", "&cThis role does not exists (%role%)");
        setProperty(message + "verify_syntax_help", "&cPlease use /verify [Role] [Discord#Tag]");
        setProperty(message + "unverify_syntax_help", "&cPlease use /verify [Role] [Discord#Tag]");
        setProperty(message + "verify_successful", "&aYou registered yourself as %role% with the Tag %tag%");
        setProperty(message + "verify_successful_description", "&aPlease text the Discord Bot !verify %username% to connect your account");
        setProperty(message + "unverified_success", "&aYou successfully unverified your account");
        setProperty(message + "join_message", "&aPlease select your role");
        setProperty(message + "connecting_enter_creds", "&aPlease Type in your Discord Tag in the Chat like Salty#0299");

        setProperty(inventory + "role_select_inv_name", "&aPlease select your role");
        setProperty(inventory + "inv_lore_description", "&8About this Role: %description%");
        setProperty(item + "inv_place_holder", new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, " ").setDamage((short)15).checkout());
        setProperty(item + "role_title_item", new ItemBuilder(Material.FEATHER, "&eSelect your role").checkout());
    }

    private static String transformMessage(String string) {
        String message = string;
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static boolean create() {
        if(!logs.exists()) {
            try {
                logs.createNewFile();
                loadDefaultConfig();
                save();
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
