package org.monstercraft.party.plugin.wrappers;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Party {

	private Player owner;

	private final String name;

	private final String password;

	private final boolean inviteOnly;

	private ArrayList<Player> members = new ArrayList<Player>();

	private ArrayList<Player> invites = new ArrayList<Player>();

	public Party(final Player owner, final String name) {
		this(owner, name, "");
	}

	public Party(final Player owner, final String name, final String password) {
		this(owner, name, password, false);
	}

	public Party(final Player owner, final String name, final String password,
			final boolean inviteOnly) {
		this.name = name;
		this.inviteOnly = inviteOnly;
		this.owner = owner;
		this.password = password;
		this.members.add(owner);
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public boolean isInviteOnly() {
		return inviteOnly;
	}

	public Player getOwner() {
		return owner;
	}

	public void addMember(final Player player) {
		if (invites.contains(player)) {
			invites.remove(player);
		}
		members.add(player);
	}

	public void removeMember(final Player p) {
		members.remove(p);
	}

	public boolean containsMember(final Player p) {
		return members.contains(p);
	}

	public void setOwner(final Player owner) {
		this.owner = owner;
	}

	public void sendPartyChat(final Player player, final String message) {
		for (Player p : members) {
			p.sendMessage(ChatColor.GREEN + "(" + ChatColor.RESET
					+ player.getDisplayName() + ChatColor.GREEN + ") "
					+ message);
		}
	}

	public void sendPartyMessage(final String message) {
		for (Player p : members) {
			p.sendMessage(message);
		}
	}

	public boolean isInvited(final Player player) {
		if (invites.contains(player)) {
			invites.remove(player);
		}
		return invites.contains(player);
	}

	public void invite(final Player player) {
		invites.add(player);
	}

	public String listMembers() {
		String s = "";
		for (Player p : members) {
			s += p.getDisplayName() + ", ";
		}
		return s.substring(0, s.length() - 2);
	}

	public boolean isEmpty() {
		return members.isEmpty();
	}
}
