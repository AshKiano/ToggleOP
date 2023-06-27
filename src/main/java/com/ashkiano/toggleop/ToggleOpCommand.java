package com.ashkiano.toggleop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//TODO přidat hlášky do configu
//TODO přidat překlady
//TODO přidat možnosti časů ve kterých si může dát OP, mimo tento interval se OP odebere
// This class handles the /toggleop command.
public class ToggleOpCommand implements CommandExecutor {

    private ToggleOp plugin;
    private String noPermissionMessage;
    private String nonPlayerMessage;
    private String revokeOpMessage;
    private String grantOpMessage;

    // This constructor takes a reference to the plugin instance and reads
    // the messages from the config.
    public ToggleOpCommand(ToggleOp plugin) {
        this.plugin = plugin;
        // Load the messages from the config
        this.noPermissionMessage = plugin.getConfig().getString("messages.noPermission", "You do not have permission to use this command!");
        this.nonPlayerMessage = plugin.getConfig().getString("messages.nonPlayer", "Only players can use this command!");
        this.revokeOpMessage = plugin.getConfig().getString("messages.revokeOp", "OP status has been revoked.");
        this.grantOpMessage = plugin.getConfig().getString("messages.grantOp", "OP status has been granted.");
    }

    // This method handles the /toggleop command.
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the command sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage(nonPlayerMessage);
            return true;
        }

        Player player = (Player) sender;
        // Check if the player has permission to use the /toggleop command
        String permission = plugin.getConfig().getString("permission", "toggleop");
        if (player.hasPermission(permission)) {
            // Check if the player has OP status
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