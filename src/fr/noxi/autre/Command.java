package fr.noxi.autre;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.noxi.Main;

public class Command implements CommandExecutor {
	private FileConfiguration config;
	private Main pl;
	
	 public Command(Main main) {
		 this.pl = main;
		 this.config = pl.getConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String msg, String[] args) {
		if(sender instanceof Player){
			 Player p = (Player)sender;
			 this.config = pl.getConfig();
				if(args.length == 1){
					 if(args[0].equalsIgnoreCase("1")){
						 config.set("locations.spawn.base1.x", p.getLocation().getX());
						 config.set("locations.spawn.base1.y", p.getLocation().getY());
						 config.set("locations.spawn.base1.z", p.getLocation().getZ());
						 config.set("locations.spawn.base1.p", p.getLocation().getPitch());
						 config.set("locations.spawn.base1.ya", p.getLocation().getYaw());
						 pl.saveConfig();
						 p.sendMessage("Spawn 1 bien set");
						
					 }
					 if(args[0].equalsIgnoreCase("2")){
						 config.set("locations.spawn.base2.x", p.getLocation().getX());
						 config.set("locations.spawn.base2.y", p.getLocation().getY());
						 config.set("locations.spawn.base2.z", p.getLocation().getZ());
						 config.set("locations.spawn.base2.p", p.getLocation().getPitch());
						 config.set("locations.spawn.base2.ya", p.getLocation().getYaw());

						 pl.saveConfig();
						 p.sendMessage("Spawn 2 bien set");
					 }
					 if(args[0].equalsIgnoreCase("lobby")){
						 config.set("locations.spawn.lobby.x", p.getLocation().getX());
						 config.set("locations.spawn.lobby.y", p.getLocation().getY());
						 config.set("locations.spawn.lobby.z", p.getLocation().getZ());
						 pl.saveConfig();
						 p.sendMessage("Lobby bien set");

					 }
					
					 
					
					if(args[0].equalsIgnoreCase("epee")){
						ItemStack truc1 = new ItemStack(Material.DIAMOND_SWORD, 1);
						ItemMeta truc1M = truc1.getItemMeta();
						truc1M.setDisplayName("§cFight§c§lSword");
						truc1M.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
						truc1.setItemMeta(truc1M);
						p.getInventory().addItem(truc1);
					}

		 }else{
			 p.sendMessage("Il y a besoin d'arguments ");
		 }
	}else{
		System.out.println("Tu es une console, tu ne peux pas éxecutre de commande");
	}
		return false;

	}
}