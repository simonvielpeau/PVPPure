package fr.noxi;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.noxi.api.Gun;
import fr.noxi.autre.Command;
import fr.noxi.autre.Locations;
import fr.noxi.events.EventsManager;
import fr.noxi.events.PVPWinner;
import fr.noxi.score.ScoreboardSign;

public class Main extends JavaPlugin{
	public ArrayList<Player> playersList = new ArrayList<>();
	public HashMap<Player, Integer> life = new HashMap<>();
	public Map<Player, ScoreboardSign> boards = new HashMap<>();
	public static Main instance;
	public static Main getInstance(){
		return instance;
	}
	@Override
	public void onEnable(){
		getCommand("setspawn").setExecutor(new Command(this));
		super.onEnable();
		instance = this;
		GameState.setState(GameState.LOBBY);
		getConfig().options().copyDefaults(true);
		saveConfig();
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Bukkit.getWorld("world").setTime(0);
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule keepInventory true");
		life.clear();
		new EventsManager(this).registerEvents();
		new PVPWinner();
		new Gun();
		new Locations();
	}
	@Override
	public void onDisable(){
		super.onDisable();
	}

	public void TeleportBungee(Player player, String serv){
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		
		try{
			out.writeUTF("Connect");
			out.writeUTF(serv);
			player.sendPluginMessage(this, "BungeeCord", b.toByteArray());
		}catch(IOException e){
			player.sendPluginMessage(this, "BungeeCord", b.toByteArray());
		}
	}
}
