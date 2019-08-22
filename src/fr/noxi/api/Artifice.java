package fr.noxi.api;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import org.bukkit.inventory.meta.FireworkMeta;


public class Artifice implements Listener {

	public void Artifce(){
		for(Player p : Bukkit.getOnlinePlayers()){
			Firework f = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
			f.detonate();
			FireworkMeta fM = f.getFireworkMeta();
			FireworkEffect effect = FireworkEffect.builder()
					.flicker(true)
					.withColor(Color.BLUE)
					.withFade(Color.YELLOW)
					.with(Type.STAR)
					.trail(false)
					.build();
			fM.setPower(1);
			fM.addEffect(effect);
			f.setFireworkMeta(fM);
		}
	
	}
}	
