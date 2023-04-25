package me.peoplexrcb.magiccrystal.configuration;

import org.bukkit.Material;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.SubSection;

import java.util.List;

public interface SettingsConfiguration {
    @ConfDefault.DefaultInteger(10)
    int minCrystalSpawnHeight();

    @ConfDefault.DefaultInteger(65)
    int maxCrystalSpawnHeight();

    @ConfDefault.DefaultInteger(3)
    int maxCrystalsAtChunk();

    @SubSection
    ItemSection item();

    @SubSection
    BlockSection block();

    @SubSection
    interface ItemSection {
        @ConfDefault.DefaultString("&aКристалл")
        String crystalItemName();

        @ConfDefault.DefaultStrings("&7Описание кристалла")
        List<String> crystalItemLore();

        @ConfDefault.DefaultString("magicCrystal")
        String crystalItemNbtTagName();

        @ConfDefault.DefaultString("ENDER_EYE")
        Material crystalItemMaterial();
    }

    @SubSection
    interface BlockSection {
        @ConfDefault.DefaultString("magicCrystalMetadata")
        String crystalBlockMetadataName();

        @ConfDefault.DefaultString("END_CRYSTAL")
        Material crystalBlockMaterial();
    }
}