package org.monstercraft.party.plugin.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.monstercraft.party.plugin.PartyAPI;
import org.monstercraft.party.plugin.command.GameCommand;
import org.monstercraft.party.plugin.wrappers.Party;

public class Leave extends GameCommand {

	@Override
	public boolean canExecute(CommandSender sender, String[] split) {
		return split.length > 1 && split[0].equalsIgnoreCase("party")
				&& split[1].equalsIgnoreCase("leave");
	}

	@Override
	public boolean execute(CommandSender sender, String[] split) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You can't leave parties!");
			return true;
		}
		Player player = (Player) sender;
		Party p;
		if (!PartyAPI.inParty(player)) {
			player.sendMessage(ChatColor.RED + "You're not in a party!");
			return true;
		}
		if ((p = PartyAPI.getParty(player)) != null) {
			p.removeMember(player);
			if (p.isEmpty()) {
				PartyAPI.removeParty(p);
			}
			player.sendMessage(ChatColor.GREEN + "You have left the party!");
			return true;
		}
		player.sendMessage(ChatColor.RED + "Invalid command usage!");
		return true;
	}

	@Override
	public String[] getPermission() {
		return new String[] { "monsterparty.leave" };
	}

}
