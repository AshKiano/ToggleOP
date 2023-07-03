package com.ashkiano.toggleop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

//TODO přidat překlady
//TODO přidat možnosti časů ve kterých si může dát OP, mimo tento interval se OP odebere

// This class handles the /toggleop command.
public class ToggleOpCommand implements CommandExecutor {

    private ToggleOp plugin;
    private FileConfiguration langConfig;
    private String noPermissionMessage;
    private String nonPlayerMessage;
    private String revokeOpMessage;
    private String grantOpMessage;

    public ToggleOpCommand(ToggleOp plugin, String languageCode) {
        this.plugin = plugin;
        // Load the messages from the language specific config
        File langFile = new File(plugin.getDataFolder() + "/languages", languageCode + ".yml");
        this.langConfig = YamlConfiguration.loadConfiguration(langFile);

        this.noPermissionMessage = langConfig.getString("messages.noPermission", "You do not have permission to use this command!");
        this.nonPlayerMessage = langConfig.getString("messages.nonPlayer", "Only players can use this command!");
        this.revokeOpMessage = langConfig.getString("messages.revokeOp", "OP status has been revoked.");
        this.grantOpMessage = langConfig.getString("messages.grantOp", "OP status has been granted.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(nonPlayerMessage);
            return true;
        }

        Player player = (Player) sender;
        String permission = plugin.getConfig().getString("permission", "toggleop");
        if (player.hasPermission(permission)) {
            if (player.isOp()) {
                player.setOp(false);
                player.sendMessage(revokeOpMessage);
            } else {
                player.setOp(true);
                player.sendMessage(grantOpMessage);
            }
        } else {
            player.sendMessage(noPermissionMessage);
        }

        return true;
    }
}