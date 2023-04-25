package me.peoplexrcb.magiccrystal;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.peoplexrcb.magiccrystal.configuration.ItemCraftWithCrystalConfiguration;
import me.peoplexrcb.magiccrystal.configuration.SettingsConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PluginInjector extends AbstractModule {
    Plugin plugin;
    SettingsConfiguration configuration;
    Map<String, ItemCraftWithCrystalConfiguration> craftConfigurationMap;

    @Override
    protected void configure() {
        bind(Plugin.class).toInstance(plugin);
        bind(SettingsConfiguration.class).toInstance(configuration);

        MapBinder<String, ItemCraftWithCrystalConfiguration> craftConfigurationMapBinder = MapBinder.newMapBinder(binder(), String.class, ItemCraftWithCrystalConfiguration.class);
        craftConfigurationMap.forEach((name, configuration) -> craftConfigurationMapBinder.addBinding(name).toInstance(configuration));
    }
}