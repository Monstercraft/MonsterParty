package org.monstercraft.party.plugin.command.commands;

import org.bukkit.command.CommandSender;
import org.monstercraft.party.plugin.command.GameCommand;

public class Invite extends GameCommand {

	@Override
	public boolean canExecute(CommandSender sender, String[] split) {
		return split.length > 2 && split[0].equalsIgnoreCase("party")
				&& split[1].equalsIgnoreCase("invite");
	}

	@Override
	public boolean execute(CommandSender sender, String[] split) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getPermission() {
		// TODO Auto-generated method stub
		return null;
	}

}
