package fr.noxi.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.noxi.GameState;
import fr.noxi.Main;



public class PVPWinner {
	static int task1;
	
	
	public static void Check(){
		if(Main.getInstance().playersList.size() == 0){
			if(!GameState.isState(GameState.LOBBY)){
				GameState.setState(GameState.FINISH);
				task1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){
					int ti = 0;
					@Override
					public void run() {
						ti++;
						if(ti == 23){
							Bukkit.spigot().restart();
							ti =  0;
							Bukkit.getScheduler().cancelTask(task1);	
						}else{
							for(Player pl : Bukkit.getOnlinePlayers()){
								Main.getInstance().TeleportBungee(pl, "hub");
							}	
						}
					}
					
				},20,20);
				
			}
			
		}
		if(Main.getInstance().playersList.size() == 1){
			if(!GameState.isState(GameState.LOBBY)){
				GameState.setState(GameState.FINISH);
				task1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){
					int ti = 0;
					@Override
					public void run() {
						ti++;
						if(ti == 23){
							Bukkit.spigot().restart();
							ti =  0;
							Bukkit.getScheduler().cancelTask(task1);	
						}else{
							for(Player pl : Bukkit.getOnlinePlayers()){
								Main.getInstance().TeleportBungee(pl, "hub");
							}	
						}
					}
					
				},20,20);	
			}
			
			
		}
		
	}
}
