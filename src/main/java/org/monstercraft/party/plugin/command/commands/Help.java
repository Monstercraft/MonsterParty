package org.monstercraft.party.plugin.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.monstercraft.party.plugin.command.GameCommand;

/**
 * This class is used for the /party help
 *
 * @author Jurre1996 <Jurre@koetse.eu>
 *
 */

public class Help extends GameCommand {

    public static void help(final CommandSender player) {
        player.sendMessage(ChatColor.DARK_PURPLE
                + "-----------------------------------------------------");
        player.sendMessage(ChatColor.RED
                + "                          MonsterParty Help");
        player.sendMessage(ChatColor.DARK_PURPLE
                + "-----------------------------------------------------");
        if (player.hasPermission("monsterparty.create")) {
            player.sendMessage(ChatColor.YELLOW + "/party create [Name] "
                    + ChatColor.AQUA + "- Create a party");
            player.sendMessage(ChatColor.YELLOW
                    + "/party create [Name] p:[Password] " + ChatColor.AQUA
                    + "- Password a Party");
            player.sendMessage(ChatColor.YELLOW + "/party create [name] -lock "
                    + ChatColor.AQUA + "- Lock your party");
        }
        if (player.hasPermission("monsterparty.join")) {
            player.sendMessage(ChatColor.YELLOW + "/party join [Name] "
                    + ChatColor.AQUA + "- Join a party");
        }
        if (player.hasPermission("monsterparty.leave")) {
            player.sendMessage(ChatColor.YELLOW + "/party leave [Name] "
                    + ChatColor.AQUA + "- Leave a party");
        }
        if (player.hasPermission("monsterparty.list")) {
            player.sendMessage(ChatColor.YELLOW + "/party list "
                    + ChatColor.AQUA
                    + "- List available parties or members in your party");
        }
        if (player.hasPermission("monsterparty.list")) {
            player.sendMessage(ChatColor.YELLOW + "/party list [party] "
                    + ChatColor.AQUA + "- List members of the specific party");
        }
        if (player.hasPermission("monsterparty.invite")) {
            player.sendMessage(ChatColor.YELLOW + "/party invite [Player] "
                    + ChatColor.AQUA + "- Invite a player to the party [Owner]");
        }
        if (player.hasPermission("monsterparty.kick")) {
            player.sendMessage(ChatColor.YELLOW + "/party kick [Player] "
                    + ChatColor.AQUA + "- Kick a player from the party [Owner]");
        }
        if (player.hasPermission("monsterparty.teleport")) {
            player.sendMessage(ChatColor.YELLOW + "/party teleport [player] "
                    + ChatColor.AQUA + "- Teleport to a party member");
            player.sendMessage(ChatColor.YELLOW + "/ptp [Player] "
                    + ChatColor.AQUA + "- Teleport to a party member");
        }
        if (player.hasPermission("monsterparty.chat")) {
            player.sendMessage(ChatColor.YELLOW + "/pc [msg] " + ChatColor.AQUA
                    + "- Send a message to party chat");
            player.sendMessage(ChatColor.YELLOW + "/pc " + ChatColor.AQUA
                    + "- Toggle PartyChat");
        }
        player.sendMessage(ChatColor.DARK_PURPLE
                + "-----------------------------------------------------");
        player.sendMessage(ChatColor.WHITE + "Plugin by: " + ChatColor.RED
                + "Fletch_to_99 " + ChatColor.WHITE + "Help Created by: "
                + ChatColor.RED + "Jurre1996");
        player.sendMessage(ChatColor.DARK_PURPLE
                + "-----------------------------------------------------");
    }

    @Override
    public boolean canExecute(final CommandSender sender, final String[] split) {
        return split.length > 1 && split[0].equalsIgnoreCase("party")
                && split[1].equalsIgnoreCase("help");
    }

    @Override
    public boolean execute(final CommandSender sender, final String[] split) {
        Help.help(sender);
        return true;
    }

    @Override
    public String[] getPermission() {
        return new String[] { "monsterparty.help" };
    }
}
