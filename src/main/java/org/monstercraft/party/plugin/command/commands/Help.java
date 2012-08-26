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
		player.sendMessage(ChatColor.DARK_PURPLE + "-----------------------------------------------------");
		player.sendMessage(ChatColor.RED + "                          MonsterParty Help");
		player.sendMessage(ChatColor.DARK_PURPLE + "-----------------------------------------------------");
		//check if user has permission to create party
		if (player.hasPermission("monsterparty.create")) { 
			player.sendMessage(ChatColor.YELLOW + "/party create [Name] " + ChatColor.AQUA + "- Create a party");
			player.sendMessage(ChatColor.YELLOW + "/party create [Name] :p[Password] " + ChatColor.AQUA + "- Create Password Protected Party");
			player.sendMessage(ChatColor.YELLOW + "/party lock " + ChatColor.AQUA + "- Lock your party");
			}else{}			
	//check if user has permission to join party
		if (player.hasPermission("monsterparty.join")) {
			player.sendMessage(ChatColor.YELLOW + "/party join [Name] " + ChatColor.AQUA + "- Join a party");
			}else{}	
	//check if user has permission to leave party
		if (player.hasPermission("monsterparty.leave")) {
			player.sendMessage(ChatColor.YELLOW + "/party join [Name] " + ChatColor.AQUA + "- Join a party");
			}else{}	
	//check if user has permission to list party
		if (player.hasPermission("monsterparty.list")) {
			player.sendMessage(ChatColor.YELLOW + "/party list " + ChatColor.AQUA + "- List available parties or members in your party");
			}else{}	
	//check if user has permission to invite to party
		if (player.hasPermission("monsterparty.invite")) {
			player.sendMessage(ChatColor.YELLOW + "/party invite [Player] " + ChatColor.AQUA + "- Invite a player to the party [Owner]");
			}else{}	
	//check if user has permission to kick from party
		if (player.hasPermission("monsterparty.kick")) {
			player.sendMessage(ChatColor.YELLOW + "/party kick [Player] " + ChatColor.AQUA + "- Kick a player from the party [Owner]");
			}else{}	
		
	//check if user has permission to teleport
		if (player.hasPermission("monsterparty.teleport")) {
			player.sendMessage(ChatColor.YELLOW + "/party teleport [player] " + ChatColor.AQUA + "- Teleport to a party member");
			player.sendMessage(ChatColor.YELLOW + "/ptp [Player] " + ChatColor.AQUA + "- Teleport to a party member");
			}else{}
	//check if user has permission to use party chat
		if (player.hasPermission("monsterparty.chat")) {
			player.sendMessage(ChatColor.YELLOW + "/pc [msg] " + ChatColor.AQUA + "- Send a message to party chat");
			player.sendMessage(ChatColor.YELLOW + "/pc " + ChatColor.AQUA + "- Toggle PartyChat");
			}else{}		
	//Send Credits
		player.sendMessage(ChatColor.DARK_PURPLE + "-----------------------------------------------------");
		player.sendMessage(ChatColor.WHITE + "Plugin by: " + ChatColor.RED + "Fletch_to_99 " + ChatColor.WHITE + "Help Created by: " + ChatColor.RED + "Jurre1996");
		player.sendMessage(ChatColor.DARK_PURPLE + "-----------------------------------------------------");
		return true;
	}

	@Override
	public String[] getPermission() {
		return new String[] { "monsterparty.help" };
	}
}
