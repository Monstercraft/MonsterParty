package org.monstercraft.party.plugin.managers;

import java.util.LinkedList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.monstercraft.party.MonsterParty;
import org.monstercraft.party.plugin.command.GameCommand;
import org.monstercraft.party.plugin.command.commands.Create;
import org.monstercraft.party.plugin.command.commands.Help;
import org.monstercraft.party.plugin.command.commands.Invite;
import org.monstercraft.party.plugin.command.commands.Join;
import org.monstercraft.party.plugin.command.commands.Kick;
import org.monstercraft.party.plugin.command.commands.Leave;
import org.monstercraft.party.plugin.command.commands.ListMembers;
import org.monstercraft.party.plugin.command.commands.Message;
import org.monstercraft.party.plugin.command.commands.Teleport;

/**
 * This class manages all of the plugins commands.
 * 
 * @author fletch_to_99 <fletchto99@hotmail.com>
 * 
 */
public class CommandManager {

	private LinkedList<GameCommand> gameCommands = new LinkedList<GameCommand>();

	/**
	 * Creates an instance
	 * 
	 * @param plugin
	 *            The parent plugin.
	 */
	public CommandManager() {
		gameCommands.add(new Create());
		gameCommands.add(new Join());
		gameCommands.add(new Leave());
		gameCommands.add(new Invite());
		gameCommands.add(new Kick());
		gameCommands.add(new Teleport());
		gameCommands.add(new ListMembers());
		gameCommands.add(new Message());
		gameCommands.add(new Help());
	}

	/**
	 * Executes a command that was ran in game or through the console.
	 * 
	 * @param sender
	 *            The command sender.
	 * @param command
	 *            The command.
	 * @param label
	 *            The commands label.
	 * @param args
	 *            The arguments in the command.
	 * @return True if the command executed successfully; Otherwise false.
	 */
	public boolean onGameCommand(final CommandSender sender,
			final Command command, final String label, final String[] args) {
		String[] split = new String[args.length + 1];
		split[0] = label;
		for (int i = 0; i < args.length; i++) {
			split[i + 1] = args[i];
		}
		for (GameCommand c : gameCommands) {
			if (c.canExecute(sender, split)) {
				if (hasPerms(sender, c)) {
					try {
						c.execute(sender, split);
						return true;
					} catch (Exception e) {
						MonsterParty.debug(e);
					}
				} else {
					return true;
				}
			}
		}
		sender.sendMessage(ChatColor.RED
				+ "Command not found here is a list of avaliable commands!");
		Help.help(sender);
		return true;
	}

	private boolean hasPerms(CommandSender sender, GameCommand command) {
		if (sender instanceof Player) {
			if (sender.hasPermission("monsterparty.*")) {
				return true;
			}
			for (String permission : command.getPermission()) {
				if (!sender.hasPermission(permission)) {
					sender.sendMessage(ChatColor.RED
							+ "You don't have permission to perform this command.");
					return false;
				}
			}
		}
		return true;
	}
}