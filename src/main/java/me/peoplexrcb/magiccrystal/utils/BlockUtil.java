package me.peoplexrcb.magiccrystal.utils;

import me.peoplexrcb.magiccrystal.MagicCrystalPlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockUtil {
    public static Block spawnMetadataBlockAt(Material material, Location location, BlockBooleanMetadata metadata) {
        Block block = location.getBlock();

        block.setType(material);
        block.setMetadata(metadata.getKey(), metadata.getValue());

        return block;
    }

    public static void removeMetadataBlockAt(Location location, String metadataKey) {
        Block block = location.getBlock();

        block.setType(Material.AIR);
        block.removeMetadata(metadataKey, MagicCrystalPlugin.getInstance().getPlugin());
    }
}