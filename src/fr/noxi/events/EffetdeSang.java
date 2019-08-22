package fr.noxi.events;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EffetdeSang implements Listener {


	@EventHandler
	public void onEffetSanguin(EntityDamageByEntityEvent e){
		
		
		Entity entity = e.getEntity();
		Location entityloc = entity.getLocation();
		
		if(entity.getType() != EntityType.ITEM_FRAME){
			entity.getWorld().playEffect(entityloc, Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
		}
		
	}
}
