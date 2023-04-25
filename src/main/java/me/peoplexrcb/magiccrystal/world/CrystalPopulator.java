package me.peoplexrcb.magiccrystal.world;

import me.peoplexrcb.magiccrystal.MagicCrystalPlugin;
import me.peoplexrcb.magiccrystal.utils.BlockBooleanMetadata;
import me.peoplexrcb.magiccrystal.utils.BlockUtil;
import me.peoplexrcb.magiccrystal.utils.MathUtil;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CrystalPopulator extends BlockPopulator {
    @Override
    public void populate(World world, Random random, Chunk chunk) {
        int amount = random.nextInt(MagicCrystalPlugin.getInstance().getSettingsConfiguration().maxCrystalsAtChunk())+1;

        for (int i = 0; i < amount; i++) {
            int y = MathUtil.random(MagicCrystalPlugin.getInstance().getSettingsConfiguration().minCrystalSpawnHeight(), MagicCrystalPlugin.getInstance().getSettingsConfiguration().maxCrystalSpawnHeight());
            int x = random.nextInt(15);
            int z = random.nextInt(15);

            Location location = new Location(world, x, y, z);

            BlockUtil.spawnMetadataBlockAt(
                    MagicCrystalPlugin.getInstance().getSettingsConfiguration().block().crystalBlockMaterial(),
                    location,
                    BlockBooleanMetadata.builder()
                            .key(MagicCrystalPlugin.getInstance().getSettingsConfiguration().block().crystalBlockMetadataName())
                            .value(BlockBooleanMetadata.BlockBooleanMetadataValue
                                    .builder()
                                    .plugin(MagicCrystalPlugin.getInstance().getPlugin())
                                    .value(true)
                                    .build())
                            .build());
        }
    }
}
