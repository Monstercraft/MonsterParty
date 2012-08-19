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
import org.monstercraft.party.MonsterParty;
import org.monstercraft.party.plugin.PartyAPI;
import org.monstercraft.party.plugin.wrappers.Party;

public class MonsterPartyListener implements Listener {

	final MonsterParty plugin;

	public MonsterPartyListener(MonsterParty plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		Party p;
		if (player != null) {
			if (PartyAPI.inParty(player)) {
				if (PartyAPI.getPartyChatMode(player)) {
					if ((p = PartyAPI.getParty(player)) != null) {
						p.sendPartyChat(player, event.getMessage());
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onPlayerDamage(EntityDamageEvent event) {
		Party p = null;
		Player hurt = null;
		Player attacker = null;
		DamageCause cause = event.getCause();
		Entity hurtEntity = event.getEntity();
		if (hurtEntity instanceof Tameable) {
			Tameable pet = (Tameable) hurtEntity;
			if (pet.getOwner() instanceof Player) {
				hurt = (Player) pet.getOwner();
			}
		}
		if (cause == DamageCause.ENTITY_ATTACK) {
			EntityDamageByEntityEvent damager = (EntityDamageByEntityEvent) event;
			Entity attackEntity = (Entity) damager.getDamager();
			if (attackEntity instanceof Tameable) {
				Tameable pet = (Tameable) attackEntity;
				if (pet.getOwner() instanceof Player) {
					attacker = (Player) pet.getOwner();
				}
			} else if (attackEntity instanceof Player) {
				attacker = (Player) attackEntity;
			}
		} else if (cause == DamageCause.PROJECTILE) {
			Projectile arrow = (Projectile) ((EntityDamageByEntityEvent) event)
					.getDamager();
			if (arrow.getShooter() instanceof Player) {
				attacker = (Player) arrow.getShooter();
			}
		}
		if (hurt != null) {
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

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onCommand(PlayerCommandPreprocessEvent event) {
		String msg = event.getMessage().toLowerCase();
		if (msg.startsWith("/ptp")) {
			event.setMessage(event.getMessage().replace("/ptp", "/party teleport"));
		} else if (msg.startsWith("/p ")) {
			event.setMessage(event.getMessage().replace("/p ", "/party "));
		}
	}
}
