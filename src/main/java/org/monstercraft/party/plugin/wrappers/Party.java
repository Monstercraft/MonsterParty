package org.monstercraft.party.plugin.wrappers;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Party {

    private Player owner;

    private final String name;

    private final String password;

    private final boolean inviteOnly;

    private final ArrayList<Player> members = new ArrayList<Player>();

    private final ArrayList<Player> invites = new ArrayList<Player>();

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
        members.add(owner);
    }

    public void addMember(final Player player) {
        if (invites.contains(player)) {
            invites.remove(player);
        }
        members.add(player);
    }

    public boolean containsMember(final Player p) {
        return members.contains(p);
    }

    public String getName() {
        return name;
    }

    public Player getOwner() {
        return owner;
    }

    public String getPassword() {
        return password;
    }

    public void invite(final Player player) {
        invites.add(player);
    }

    public boolean isEmpty() {
        return members.isEmpty();
    }

    public boolean isInvited(final Player player) {
        return invites.contains(player);
    }

    public boolean isInviteOnly() {
        return inviteOnly;
    }

    public String listMembers() {
        if (!this.isEmpty()) {
            int total = 0;
            String list = "";
            for (final Player p : members) {
                if (p.getName().equalsIgnoreCase(owner.getName())) {
                    total++;
                    continue;
                }
                if (p.hasPermission("monsterparty.admin")) {
                    continue;
                }
                total++;
                list += p.getName() + ", ";
            }
            final String s = ChatColor.GREEN + "Party Members (" + total
                    + "): " + ChatColor.BLUE + owner.getName() + ", "
                    + ChatColor.GREEN + list;
            return total > 0 ? s.substring(0, s.length() - 2) : ChatColor.GREEN
                    + "Nobody found here... Heh a party of admins.";
        }
        return "";
    }

    public void removeMember(final Player p) {
        members.remove(p);
    }

    public void sendPartyChat(final Player player, final String message) {
        if (!this.isEmpty()) {
            for (final Player p : members) {
                p.sendMessage(ChatColor.GREEN + "(" + ChatColor.RESET
                        + player.getDisplayName() + ChatColor.GREEN + ") "
                        + message);
            }
        }
    }

    public void sendPartyMessage(final String message) {
        if (!this.isEmpty()) {
            for (final Player p : members) {
                p.sendMessage(message);
            }
        }
    }

    public void setNewOwner() {
        if (!this.isEmpty()) {
            for (final Player p : members) {
                this.setOwner(p);
                return;
            }
        }
    }

    public void setOwner(final Player owner) {
        this.owner = owner;
    }
}
