package us.rjks.utils;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.rjks.discord.Config;

import java.util.ArrayList;

/***************************************************************************
 *
 *  Urheberrechtshinweis
 *  Copyright Ⓒ Robert Kratz 2021
 *  Erstellt: 23.04.2021 / 17:18
 *
 **************************************************************************/

public class Inventory {

    public static void openRoleSelect(Player player) {
        org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, getInvSizeFromElementAmount(Role.getKits().size()), Config.getInventory("role_select_inv_name").toString());

        for (int i = 0; i < 9; i++) {
            inv.setItem(i, Config.getItemStack("inv_place_holder"));
        }

        inv.setItem(4, Config.getItemStack("role_title_item"));

        int i = 9;
        for (Role.RoleSchem role : Role.getKits()) {
            ItemStack item = role.getIcon();
            ItemMeta meta = item.getItemMeta();

            ArrayList lore = new ArrayList();

            lore.add(Config.getInventory("inv_lore_description").replaceAll("%description%", role.getDescription()));
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            item.setItemMeta(meta);

            inv.setItem(i, item);
            i++;
        }

        player.openInventory(inv);
    }

    public static int getInvSizeFromElementAmount(int i) {
        if (i < 9) {
            return 9*2;
        } else if (i < 18) {
            return 9*3;
        } else if (i < 27) {
            return 9*4;
        } else if (i < 36) {
            return 9*5;
        } else if (i < 45) {
            return 9*6;
        } else if (i < 54) {
            return 9*7;
        }
        return 9*2;
    }
}
