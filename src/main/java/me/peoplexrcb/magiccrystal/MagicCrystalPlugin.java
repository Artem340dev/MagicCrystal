package me.peoplexrcb.magiccrystal;

import com.google.inject.Inject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.peoplexrcb.magiccrystal.configuration.ItemCraftWithCrystalConfiguration;
import me.peoplexrcb.magiccrystal.configuration.SettingsConfiguration;
import me.peoplexrcb.magiccrystal.utils.ItemUtil;
import me.peoplexrcb.magiccrystal.world.CrystalGenerator;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MagicCrystalPlugin {
    @Getter
    static MagicCrystalPlugin instance;

    @Getter
    Plugin plugin;

    @Getter
    @Inject
    SettingsConfiguration settingsConfiguration;

    @Getter
    @Inject
    Map<String, ItemCraftWithCrystalConfiguration> craftConfigurations;

    public void onEnable() {
        instance = this;

        craftConfigurations.forEach((name, configuration) -> {
            ShapedRecipe recipe = new ShapedRecipe(ItemUtil.getItem(configuration.result().itemResultName(), configuration.result().itemResultLore(), configuration.result().itemResultMaterial(), null))
                    .shape(configuration.craftMap());

            configuration.craftElements().forEach((key, craftElement) -> recipe.setIngredient(key.charAt(0), craftElement.material()));

            Bukkit.addRecipe(recipe);
        });
    }

    public void onDisable() {
    }
}
