package org.monstercraft.party;

import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.monstercraft.party.plugin.listeners.MonsterPartyListener;
import org.monstercraft.party.plugin.managers.CommandManager;
import org.monstercraft.party.plugin.util.Metrics;

/**
 * This class represents the main plugin. All actions related to the plugin are forwarded by this class
 * 
 * @author Fletch_to_99 <fletchto99@hotmail.com>
 * 
 */
public class MonsterParty extends JavaPlugin {

    private CommandManager command = null;

    /**
     * Enables the plugin.
     */

    @Override
    public void onEnable() {
        MonsterParty.log("Starting plugin.");
        command = new CommandManager();
        getServer().getPluginManager().registerEvents(
                new MonsterPartyListener(this), this);
        try {
            new Metrics(this).start();
        } catch (final IOException e) {
            MonsterParty.debug(e);
        }
    }

    /**
     * Disables the plugin.
     */

    @Override
    public void onDisable() {
        MonsterParty.log("Successfully disabled plugin!");
    }

    /**
     * Handles commands.
     */

    @Override
    public boolean onCommand(final CommandSender sender, final Command command,
            final String label, final String[] args) {
        return getCommandManager().onGameCommand(sender, command, label, args);
    }

    /**
     * The plugins settings.
     * 
     * @return The settings.
     */
    protected CommandManager getCommandManager() {
        return command;
    }

    /**
     * Logs debugging messages to the console.
     * 
     * @param error
     *            The message to print.
     */
    public static void debug(final Exception error) {
        Bukkit.getLogger().log(Level.SEVERE, "Critical error detected!", error);
    }

    /**
     * Logs a message to the console.
     * 
     * @param msg
     *            The message to print.
     */
    public static void log(final String msg) {
        Bukkit.getLogger().info(msg);
    }

}
