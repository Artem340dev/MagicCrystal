package me.peoplexrcb.magiccrystal.handlers;

import me.peoplexrcb.magiccrystal.MagicCrystalPlugin;
import me.peoplexrcb.magiccrystal.utils.BlockUtil;
import me.peoplexrcb.magiccrystal.utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class WorldHandler implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!event.hasBlock()) return;
        if (!event.getAction().name().endsWith("BLOCK")) return;
        Block block = event.getClickedBlock();

        if (!block.hasMetadata(MagicCrystalPlugin.getInstance().getSettingsConfiguration().block().crystalBlockMetadataName())) return;
        BlockUtil.removeMetadataBlockAt(block.getLocation(), MagicCrystalPlugin.getInstance().getSettingsConfiguration().block().crystalBlockMetadataName());

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack item = ItemUtil.getItem(
                    MagicCrystalPlugin.getInstance().getSettingsConfiguration().item().crystalItemName(),
                    MagicCrystalPlugin.getInstance().getSettingsConfiguration().item().crystalItemLore(),
                    MagicCrystalPlugin.getInstance().getSettingsConfiguration().item().crystalItemMaterial(),
                    Map.of(MagicCrystalPlugin.getInstance().getSettingsConfiguration().item().crystalItemNbtTagName(), true)
            );

            event.getPlayer().getInventory().addItem(item);
        } else if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            block.getWorld().createExplosion(block.getLocation(), 10);
        }
    }
}