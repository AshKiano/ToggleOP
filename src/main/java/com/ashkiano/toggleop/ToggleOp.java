package com.ashkiano.toggleop;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ToggleOp extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        // Create a directory named "languages" in the plugin's data folder
        File languageFolder = new File(getDataFolder(), "languages");
        if (!languageFolder.exists()) {
            languageFolder.mkdirs();
        }

        // Check if language files exist, if not, save them from the resources
        String[] languages = {"en", "cs", "de", "sk", "es"};
        for (String lang : languages) {
            File langFile = new File(languageFolder, lang + ".yml");
            if (!langFile.exists()) {
                this.saveResource("languages/" + lang + ".yml", false);
            }
        }

        String languageCode = getConfig().getString("language", "en"); // default language is English
        this.getCommand("toggleop").setExecutor(new ToggleOpCommand(this, languageCode));

        this.getLogger().info("Thank you for using the ToggleOp plugin! If you enjoy using this plugin, please consider making a donation to support the development. You can donate at: https://paypal.me/josefvyskocil");

        Metrics metrics = new Metrics(this, 18886);
    }
}