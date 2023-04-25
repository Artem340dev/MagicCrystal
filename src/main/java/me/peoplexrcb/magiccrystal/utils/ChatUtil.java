package me.peoplexrcb.magiccrystal.utils;

import org.bukkit.ChatColor;

import java.util.List;

public class ChatUtil {

    public static List<String> parseColor(List<String> list) {
        if (list == null) return list;

        list.replaceAll(ChatUtil::parseColor);
        return list;
    }

    public static String parseColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}