package fr.noxi.api;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Gun implements Listener {




	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Snowball){
			Snowball s = (Snowball) e.getDamager();
			
			if(s.getShooter() instanceof Player){
				Player shooter = (Player) s.getShooter();
				
				if(shooter.getItemInHand().getType() == Material.DIAMOND_HOE){
					e.setDamage(5);
				}
			}
		}
	}
	
	
	@EventHandler
	public void onPINTERACT(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getItem() != null){
			if(e.getItem().getType() == Material.DIAMOND_HOE){
				if(e.getAction() == Action.RIGHT_CLICK_AIR){
					p.launchProjectile(Snowball.class);
				
				}else if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
					e.setCancelled(true);
				}else if(e.getAction() == Action.LEFT_CLICK_AIR){
					if(p.hasPotionEffect(PotionEffectType.SLOW)){
						p.removePotionEffect(PotionEffectType.SLOW);
						
					}else{
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999, 500));
					}
				}
			}else if(!(e.getItem().getType() == Material.DIAMOND_HOE)){
				return;
			}
			
				
		}
		
	}
	
}