package fr.noxi.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.inventory.ItemStack;

import fr.noxi.GameState;
import fr.noxi.Main;

public class PlayerListener implements Listener {
	@EventHandler
	public void damage(EntityDamageEvent e){
		if(!GameState.isState(GameState.GAME)){
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void place(BlockPlaceEvent e){
		Player p = e.getPlayer();
		if(!p.hasPermission("perm.admin")){
			e.setCancelled(true);
		}
			
	}
	
	@EventHandler
	public void breake(BlockBreakEvent e){
		Player p = e.getPlayer();
		if(!p.hasPermission("perm.admin")){
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void portalCreate(PortalCreateEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void spawnMob(CreatureSpawnEvent e){
		e.setCancelled(true);
	}
	@EventHandler
	public void drope(PlayerDropItemEvent e){
		Player p = e.getPlayer();
		if(p.hasPermission("perm.admin")){
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void craft(CraftItemEvent e){
		e.setCancelled(true);
	}
	@EventHandler
	public void weather(WeatherChangeEvent e){
		e.setCancelled(true);
	}
	@EventHandler
	public void interact(PlayerInteractEvent e){
		Player p = e.getPlayer();
		ItemStack i = e.getItem();
		if(i == null){
			return;
		}
		if(i != null && i.getType() != null && i.getType() == Material.BED){
			Main.getInstance().TeleportBungee(p, "hub");
			
		}
	}
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		if(GameState.isState(GameState.PVPF)){
			e.setCancelled(true);
		}
	}
	
}
