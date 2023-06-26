package com.ashkiano.toggleop;

import org.bukkit.plugin.java.JavaPlugin;

public class ToggleOp extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.getCommand("toggleop").setExecutor(new ToggleOpCommand(this));
        Metrics metrics = new Metrics(this, 18886);
    }
}
