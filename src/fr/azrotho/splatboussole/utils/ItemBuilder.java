package fr.azrotho.splatboussole.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {
    public static ItemStack create(Material material, int amount, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        item.setAmount(amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
