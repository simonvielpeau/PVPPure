package fr.noxi.events;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.noxi.GameState;
import fr.noxi.Main;
import fr.noxi.api.Title;
import fr.noxi.autre.Locations;
import net.minecraft.server.v1_9_R2.PacketPlayInClientCommand;
import net.minecraft.server.v1_9_R2.PacketPlayInClientCommand.EnumClientCommand;


public class PVP implements Listener {
	int task;
	
	@EventHandler
	public void death(PlayerDeathEvent e){
		GameState.setState(GameState.PVPF);
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule keepInventory true");
		final Player p = e.getEntity();
		Player a = e.getEntity().getKiller();
		int life = Main.getInstance().life.get(p) - 1;
		
		Main.getInstance().life.put(p, life);
		e.setDeathMessage("");
		a.sendMessage("§7[§cPvP§c§lFight§7] §cGG§7, §6tu à tué §b§l" + p.getName() + " §8!");

		p.sendMessage("§7[§cPvP§c§lFight§7] §5Oups..., §6tu es mort");
		int item = Main.getInstance().getConfig().getInt("item");
		if(item == 2){
			p.setMaxHealth(16.0);
			p.setHealth(16.0);
		}else if(item == 1){
			p.setMaxHealth(34.0);
			p.setHealth(34.0);
		}else{
			p.setMaxHealth(36.0);
			p.setHealth(36.0);
		}
	
	if(Main.getInstance().life.get(p) == 0){
		Main.getInstance().playersList.remove(p);
		
		PVPWinner.Check();
		Title.sendTitle(a, "§6BRAVO !","§cTu à gagné cette partie" , 30);
		Bukkit.broadcastMessage("§7[§cPvP§c§lFight§7] §7Bravo à §b§l" + a.getName() + " §5> §7il à gagné ce beau §c§lPvP");
	}else{
		for(final Player pl : Bukkit.getOnlinePlayers()){
			pl.setHealth(p.getMaxHealth());
			Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable(){

				@Override
				public void run() {
					Locations.teleportPlayers();
					
				}
				
			},1*20);
			task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){

				int titi = 5;
				@Override
				public void run() {
					titi--;
					if(titi > 0){
						pl.sendMessage("§7[§cPvP§c§lFight§7] §cLa partie reprend dans §e" + titi + " §cseconde§7(§cs§7) §8!");
						pl.setLevel(titi);
					}if(titi == 0){
						pl.sendMessage("§7[§cPvP§c§lFight§7] §cC'est parti §8!");
						Locations.clearChat();
						pl.setLevel(0);
						GameState.setState(GameState.GAME);
						Bukkit.getScheduler().cancelTask(task);
					}
					
				}
				
			},20,20);
		}
		
	}
		
		Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable(){
			

			@Override
			public void run() {
				
				PacketPlayInClientCommand cmd = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
				((CraftPlayer) p).getHandle().playerConnection.a(cmd);
				
			}
			
		},5L);
				
			
	}
}
