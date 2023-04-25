package me.peoplexrcb.magiccrystal.bootstrap;

import com.google.inject.Guice;
import com.google.inject.Injector;
import me.peoplexrcb.magiccrystal.MagicCrystalPlugin;
import me.peoplexrcb.magiccrystal.PluginInjector;
import me.peoplexrcb.magiccrystal.configuration.ItemCraftWithCrystalConfiguration;
import me.peoplexrcb.magiccrystal.configuration.SettingsConfiguration;
import me.peoplexrcb.magiccrystal.handlers.WorldHandler;
import me.peoplexrcb.magiccrystal.world.CrystalGenerator;
import org.bukkit.Bukkit;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.ext.snakeyaml.CommentMode;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlConfigurationFactory;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlOptions;
import space.arim.dazzleconf.helper.ConfigurationHelper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Bootstrap extends JavaPlugin {
    private MagicCrystalPlugin entryPoint;
    private CrystalGenerator generator;

    @Override
    public void onEnable() {
        try {
            SettingsConfiguration settingsConfiguration = this.initSettingsConfiguration();
            Map<String, ItemCraftWithCrystalConfiguration> craftConfigurationMap = this.initItemCraftConfigurations();

            Bukkit.getPluginManager().registerEvents(new WorldHandler(), this);

            Injector injector = Guice.createInjector(new PluginInjector(this, settingsConfiguration, craftConfigurationMap));

            this.entryPoint = injector.getInstance(MagicCrystalPlugin.class);
            entryPoint.onEnable();

            this.generator = new CrystalGenerator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        entryPoint.onDisable();
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        System.out.println("Plugin statement is " + entryPoint);
        if (worldName != "world") return super.getDefaultWorldGenerator(worldName, id);
        return generator;
    }

    private SettingsConfiguration initSettingsConfiguration() throws Exception {
        return new ConfigurationHelper<>(
                getDataFolder().toPath(),
                "settings.yml",
                SnakeYamlConfigurationFactory.create(
                        SettingsConfiguration.class,
                        ConfigurationOptions.defaults(),
                        new SnakeYamlOptions.Builder()
                                .commentMode(CommentMode.headerOnly())
                                .build()
                )
        ).reloadConfigData();
    }

    private Map<String, ItemCraftWithCrystalConfiguration> initItemCraftConfigurations() throws Exception {
        Map<String, ItemCraftWithCrystalConfiguration> configurationMap = new HashMap();

        File craftConfigsFolder = new File(this.getDataFolder(), "crafts");
        if (!craftConfigsFolder.exists()) craftConfigsFolder.mkdirs();

        for (File fileConfiguration : craftConfigsFolder.listFiles(file -> file.getName().endsWith("yml"))) {
            ItemCraftWithCrystalConfiguration configuration = new ConfigurationHelper<>(
                    getDataFolder().toPath(),
                    fileConfiguration.getName(),
                    SnakeYamlConfigurationFactory.create(
                            ItemCraftWithCrystalConfiguration.class,
                            ConfigurationOptions.defaults(),
                            new SnakeYamlOptions.Builder()
                                    .commentMode(CommentMode.headerOnly())
                                    .build()
                    )
            ).reloadConfigData();

            configurationMap.put(fileConfiguration.getName(), configuration);
        }

        return configurationMap;
    }
}
