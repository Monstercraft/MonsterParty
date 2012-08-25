package org.monstercraft.party.plugin.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.monstercraft.party.plugin.command.GameCommand;

/**
 * This class is used for the /party help
 * 
 * @author Jurre1996 <Jurre@koetse.eu>
 * 
 */

public class Help extends GameCommand {

	@Override
	public boolean canExecute(CommandSender sender, String[] split) {
		return split.length > 1 && split[0].equalsIgnoreCase("party")
				&& split[1].equalsIgnoreCase("help");
	}

	@Override
	public boolean execute(CommandSender sender, String[] split) {
		Player player = (Player) sender;
		if (player.hasPermission("monsterparty.create")) {
			sender.sendMessage(ChatColor.YELLOW + "/party create [Name] " + ChatColor.AQUA + "- Create a party");
			return true;
		}
		return false;
	}

	@Override
	public String[] getPermission() {
		return null;
	}
}
