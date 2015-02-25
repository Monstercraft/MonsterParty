package org.monstercraft.party.plugin.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.monstercraft.party.MonsterParty;
import org.monstercraft.party.plugin.PartyAPI;
import org.monstercraft.party.plugin.wrappers.Party;

public class MonsterPartyListener implements Listener {

    final MonsterParty plugin;

    public MonsterPartyListener(final MonsterParty plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onCommand(final PlayerCommandPreprocessEvent event) {
        final String msg = event.getMessage().toLowerCase();
        if (msg.startsWith("/ptp")) {
            event.setMessage(event.getMessage().replace("/ptp",
                    "/party teleport"));
        } else if (msg.startsWith("/p ")) {
            event.setMessage(event.getMessage().replace("/p ", "/party "));
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        Party p;
        if (player != null) {
            if (PartyAPI.inParty(player)) {
                if (PartyAPI.getPartyChatMode(player)) {
                    if ((p = PartyAPI.getParty(player)) != null) {
                        p.sendPartyChat(player, event.getMessage());
                        event.setCancelled(true);
                        event.setMessage("");
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerDamage(final EntityDamageEvent event) {
        Party p = null;
        Player hurt = null;
        Player attacker = null;
        final DamageCause cause = event.getCause();
        final Entity hurtEntity = event.getEntity();
        if (hurtEntity instanceof Tameable) {
            final Tameable pet = (Tameable) hurtEntity;
            if (pet.getOwner() instanceof Player) {
                hurt = (Player) pet.getOwner();
            }
        } else if (hurtEntity instanceof Player) {
            hurt = (Player) hurtEntity;
        }
        if (cause == DamageCause.ENTITY_ATTACK) {
            final EntityDamageByEntityEvent damager = (EntityDamageByEntityEvent) event;
            final Entity attackEntity = damager.getDamager();
            if (attackEntity instanceof Tameable) {
                final Tameable pet = (Tameable) attackEntity;
                if (pet.getOwner() instanceof Player) {
                    attacker = (Player) pet.getOwner();
                }
            } else if (attackEntity instanceof Player) {
                attacker = (Player) attackEntity;
            }
        } else if (cause == DamageCause.PROJECTILE) {
            final Projectile arrow = (Projectile) ((EntityDamageByEntityEvent) event)
                    .getDamager();
            if (arrow.getShooter() instanceof Player) {
                attacker = (Player) arrow.getShooter();
            }
        }
        if (hurt != null && attacker != null) {
            if (PartyAPI.inParty(hurt)) {
                if ((p = PartyAPI.getParty(hurt)) != null) {
                    if (p.containsMember(attacker)) {
                        event.setDamage(0);
                        event.setCancelled(true);
                        attacker.sendMessage(ChatColor.RED
                                + "You can't hurt members of the same party!");
                    }
                }
            }
        }
    }

    public void onPlayerQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        Party p;
        if (!PartyAPI.inParty(player)) {
            return;
        }
        if ((p = PartyAPI.getParty(player)) != null) {
            p.removeMember(player);
            if (!player.hasPermission("monsterparty.admin")) {
                p.sendPartyMessage(ChatColor.GREEN + player.getDisplayName()
                        + " has left the party!");
            }
            if (p.isEmpty()) {
                PartyAPI.removeParty(p);
            } else {
                if (p.getOwner().equals(player)) {
                    p.setNewOwner();
                    p.sendPartyMessage(ChatColor.GREEN
                            + p.getOwner().getDisplayName()
                            + " is the new party owner!");
                }
            }
        }
    }
}
