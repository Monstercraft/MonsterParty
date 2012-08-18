package org.monstercraft.party.plugin.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.monstercraft.party.plugin.PartyAPI;
import org.monstercraft.party.plugin.command.GameCommand;
import org.monstercraft.party.plugin.wrappers.Party;

public class Join extends GameCommand {

	@Override
	public boolean canExecute(CommandSender sender, String[] split) {
		return split.length > 2 && split[0].equalsIgnoreCase("party")
				&& split[1].equalsIgnoreCase("join");
	}

	@Override
	public boolean execute(CommandSender sender, String[] split) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You can't join parties!");
		}
		Player player = (Player) sender;
		Party p;
		if (PartyAPI.inParty(player)) {
			player.sendMessage(ChatColor.RED
					+ "You must leave your current party before joining another!");
			return true;
		}
		if (split.length == 3) {
			if ((p = PartyAPI.getParty(split[2])) != null) {
				p.addMember(player);
				player.sendMessage(ChatColor.GREEN
						+ "Successfully joined party!");
				return true;
			}
			player.sendMessage(ChatColor.RED
					+ "No party with that name exists!");
			return true;
		}
		player.sendMessage(ChatColor.RED + "Invalid command usage!");
		return true;
	}

	@Override
	public String[] getPermission() {
		return new String[] { "monsterparty.join" };
	}

}
