package me.peoplexrcb.magiccrystal.world;

import me.peoplexrcb.magiccrystal.utils.MathUtil;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class CrystalGenerator extends ChunkGenerator {
    @NotNull
    @Override
    public List<BlockPopulator> getDefaultPopulators(@NotNull World world) {
        List<BlockPopulator> populators = super.getDefaultPopulators(world);
        populators.addAll(List.of(new CrystalPopulator()));

        return populators;
    }
}