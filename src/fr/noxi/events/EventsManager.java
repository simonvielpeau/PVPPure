package fr.noxi.events;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import fr.noxi.Main;
import fr.noxi.api.Gun;


public class EventsManager {
	public Main pl;
	

	public EventsManager(Main main) {
		this.pl = main;
	}


	public void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PVPJoin(), pl);
		pm.registerEvents(new PlayerListener(), pl);
		pm.registerEvents(new PVP(), pl);
		pm.registerEvents(new EffetdeSang(), pl);
		pm.registerEvents(new Gun(), pl);
		
	}
}
