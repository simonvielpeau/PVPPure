package fr.noxi.autre;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import fr.noxi.Main;

public class Locations {
	public static ArrayList<Location> cages = new ArrayList<>();
	public Locations(){
		 double x1 = Main.getInstance().getConfig().getDouble("locations.spawn.base1.x");
		 double y1 = Main.getInstance().getConfig().getDouble("locations.spawn.base1.y");
		 double z1 = Main.getInstance().getConfig().getDouble("locations.spawn.base1.z");
		
		 float p1 = Main.getInstance().getConfig().getInt("locations.spawn.base1.p");
		 float ya1 = Main.getInstance().getConfig().getInt("locations.spawn.base1.ya");


		 double x2 = Main.getInstance().getConfig().getDouble("locations.spawn.base2.x");
		 double y2 = Main.getInstance().getConfig().getDouble("locations.spawn.base2.y");
		 double z2 = Main.getInstance().getConfig().getDouble("locations.spawn.base2.z");
		 float p2 = (float) Main.getInstance().getConfig().getDouble("locations.spawn.base2.p");
		 float ya2 = (float) Main.getInstance().getConfig().getDouble("locations.spawn.base2.ya");

		World w = Bukkit.getWorld("world");
		cages.add(new Location(w,x1,y1,z1, ya1, p1));
		cages.add(new Location(w,x2,y2,z2, ya2, p2));
	}
	public static void teleportPlayers(){
		for(int i = 0; i < Main.getInstance().playersList.size(); i++){
			Player player = Main.getInstance().playersList.get(i);
			Location cage = cages.get(i);
			player.teleport(cage);
			 
		}
		
	}
	public static void clearChat(){
		for(int i = 0; i < 20; i++){
			Bukkit.broadcastMessage("");
		}
	}

}
