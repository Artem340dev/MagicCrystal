package me.peoplexrcb.magiccrystal.utils;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class ItemUtil {
    public static ItemStack getItem(String name, List<String> lore, Material material, Map<String, Boolean> tags) {
        ItemStack item = setTags(new ItemStack(material), tags);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatUtil.parseColor(name));
        meta.setLore(ChatUtil.parseColor(lore));

        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack setTags(ItemStack item, Map<String, Boolean> tags) {
        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);

        NBTTagCompound tagCompound = new NBTTagCompound();
        if (tags != null) tags.forEach((key, value) ->  tagCompound.setBoolean(key, value));

        nmsItem.setTag(tagCompound);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }
}