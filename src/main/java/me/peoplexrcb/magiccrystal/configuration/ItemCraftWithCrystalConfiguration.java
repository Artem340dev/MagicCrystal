package me.peoplexrcb.magiccrystal.configuration;

import org.bukkit.Material;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.SubSection;

import java.util.List;
import java.util.Map;

public interface ItemCraftWithCrystalConfiguration {
    @ConfDefault.DefaultStrings({"", "", ""})
    String[] craftMap();

    @SubSection
    ItemResultSection result();

    @ConfDefault.DefaultObject("defaultCraftElements")
    Map<String, @SubSection CraftElementSection> craftElements();

    static Map<String, @SubSection CraftElementSection> defaultCraftElements(CraftElementSection section) {
        return Map.of("A", section);
    }

    @SubSection
    interface CraftElementSection {
        @ConfDefault.DefaultString("END_CRYSTAL")
        Material material();
    }

    @SubSection
    interface ItemResultSection {
        @ConfDefault.DefaultString("item name")
        String itemResultName();

        @ConfDefault.DefaultStrings("item lore")
        List<String> itemResultLore();

        @ConfDefault.DefaultString("AIR")
        Material itemResultMaterial();
    }
}