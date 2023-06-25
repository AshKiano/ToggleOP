package com.ashkiano.toggleop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//TODO přidat hlášky do configu
//TODO přidat překlady
//TODO přidat možnosti časů ve kterých si může dát OP, mimo tento interval se OP odebere
public class ToggleOpCommand implements CommandExecutor {

    private ToggleOp plugin;

    public ToggleOpCommand(ToggleOp plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        String permission = plugin.getConfig().getString("permission", "toggleop");
        if (player.hasPermission(permission)) {
            if (player.isOp()) {
                player.setOp(false);
                player.sendMessage("OP status has been revoked.");
            } else {
                player.setOp(true);
                player.sendMessage("OP status has been granted.");
            }
        } else {
            player.sendMessage("You do not have permission to use this command!");
        }

        return true;
    }
}
