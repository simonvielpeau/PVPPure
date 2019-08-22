package fr.noxi.events;


import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import fr.noxi.GameState;
import fr.noxi.Main;
import fr.noxi.api.Title;
import fr.noxi.autre.Locations;
import fr.noxi.score.ScoreboardSign;
import ru.tehkode.permissions.bukkit.PermissionsEx;




public class PVPJoin implements Listener{
	
	protected static final String UUID = null;
	int timer = 31
			;
	int task;
	int task1;
	int task2;
	int task3;
	int task4;
	int task10;
	@SuppressWarnings("deprecation")
	@EventHandler
	public void join(PlayerJoinEvent e){
		final Player p = e.getPlayer();
		
		String prefix = PermissionsEx.getUser(e.getPlayer()).getPrefix();
		 p.setPlayerListName(prefix + " "+p.getName());
		

		p.removePotionEffect(PotionEffectType.SLOW);
		//If trop de joueurs
		if(Bukkit.getOnlinePlayers().size() >= 3){
			task1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){
			int ti = 0;
			@Override
			public void run() {
				ti++;
				if(ti == 3){
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
			ItemStack it = new ItemStack(Material.BED);					
			ItemMeta itM = it.getItemMeta();
			itM.setDisplayName("§7Pour retourner au hub !");
			it.setItemMeta(itM);
			
			p.setLevel(0);
		    e.setJoinMessage("");
		   p.setMaxHealth(20.0);
		   p.setHealth(20.0);
		    BossBar bb = Bukkit.createBossBar("§c§lPvPFight §5> §7Mini Jeu développé par §eNo0xi §7et builder par §e" + Main.getInstance().getConfig().getString("builder")
		    		+ " §8!", BarColor.RED, BarStyle.SOLID, BarFlag.DARKEN_SKY);
			
			bb.addPlayer(p);
			bb.show();
			p.getInventory().clear();
			p.getInventory().setItem(8, it);
			final ScoreboardSign sb = new ScoreboardSign(p, "§c§lPvPFight");
			task10 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){
				String mode;
				@Override
				public void run() {
					sb.destroy();
					sb.create();
					
					sb.setLine(0, "§9");
					sb.setLine(1, "§ePlayers :");
					sb.setLine(2, Main.getInstance().playersList.size()+"");
					sb.setLine(3, "§a");
					sb.setLine(4, "Status du jeu :");
					sb.setLine(6, "§1");
					sb.setLine(9, "§6xonisland.ovh:2112");
					sb.setLine(8, "§b");
					if(GameState.isState(GameState.FINISH)){
						String state1 = "§aTerminé §8!";
						sb.setLine(5, state1);
					}else if(GameState.isState(GameState.GAME)){
						String state1 = "§aEn jeu §8!";
						sb.setLine(5, state1);
					
					}else if(GameState.isState(GameState.LOBBY)){
						String state1 = "§bEn attente...";
						sb.setLine(5, state1);
					}
					if(Main.getInstance().getConfig().getInt("item") == 1){
						mode = "§cSword§c§lMode";
						sb.setLine(7, "§7PVPFight §6> " + mode);
					}else if(Main.getInstance().getConfig().getInt("item") == 2){
						mode = "§cBow§c§lMode";
						sb.setLine(7, "§7PVPFight §6> " + mode);
					}else if(Main.getInstance().getConfig().getInt("item") == 3){
						 mode = "§cGun§c§lMode";
						 sb.setLine(7, "§7PVPFight §6> " + mode);
					}
						
					Main.getInstance().boards.put(p, sb);
				}
			},20,20);
			
			Main.getInstance().life.put(p, 3);
		

			
			
			
			
		if(!GameState.isState(GameState.LOBBY)){
			p.setGameMode(GameMode.SPECTATOR);
			Title.sendTitle(p, "§c§lPvPFight", "§cTu es §aun §5spectateur", 40);
			for(int i = 0; i < 2; i++){
				p.getInventory().clear();
			}
			return;
		}
		if(GameState.isState(GameState.LOBBY)){
			if(Main.getInstance().playersList.size() >= 3){
				p.sendMessage("§7[§6SkyWars§7] §cDésolé il y a trop de joueurs : la partie est pleine");
				Main.getInstance().TeleportBungee(p, "hub");
			}
		}
		if(!Main.getInstance().playersList.contains(p)){
			Main.getInstance().playersList.add(p);
			p.setGameMode(GameMode.SURVIVAL);
			 double x = Main.getInstance().getConfig().getDouble("locations.spawn.lobby.x");
			 double y = Main.getInstance().getConfig().getDouble("locations.spawn.lobby.y");
			 double z = Main.getInstance().getConfig().getDouble("locations.spawn.lobby.z");
			p.teleport(new Location(p.getWorld(),x,y,z));
			for(Player pl : Bukkit.getOnlinePlayers()){
				Title.sendActionBar(pl, "§e§l" + p.getName() + " §aà rejoint le jeu §7(§a"+ Main.getInstance().playersList.size() + "§7/§a2§7)");
			}
			

			
		}
		if(Main.getInstance().playersList.size() == 2){
			
			task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){

				@Override
				public void run() {
					timer --;
					for(final Player p : Bukkit.getOnlinePlayers()){
						p.setLevel(timer);
						if(timer == 30 || timer == 15 || timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1){
							Title.sendTitle(p, "§c§lPvPFight", "§6Téléportation dans §a" + timer + " §5secondes", 20);
						}
						if(timer == 0){
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule keepInventory true");
							
							 task2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){
									
									


									@Override
									public void run() {
										for(Player pl : Bukkit.getOnlinePlayers()){
										Player p1 = Main.getInstance().playersList.get(0);
										Player p2 = Main.getInstance().playersList.get(1);
											Title.sendActionBar(pl, "§e"+ p1.getName() + " §7: §c" + Main.getInstance().life.get(p1) + "§e       §e" + p2.getName() + "§7: §c" + Main.getInstance().life.get(p2));
										}
										
									} 
									
										
								},20,20);
						
							if(Main.getInstance().playersList.size() == 2){
								Bukkit.getScheduler().cancelTask(task);
								Title.sendTitle(p, "§c§lPvPFight", "§cTéléportation...", 20);
								GameState.setState(GameState.PVPF);
								Locations.teleportPlayers();
								p.getInventory().clear();
								int item = Main.getInstance().getConfig().getInt("item");
								if(item == 1){
									ItemStack truc = new ItemStack(Material.DIAMOND_SWORD, 1);
									ItemMeta trucM = truc.getItemMeta();
									trucM.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
									trucM.setDisplayName("§cFight§c§lSword");
									truc.setItemMeta(trucM);
									p.getInventory().addItem(truc);
									p.setMaxHealth(34.0);
									p.setHealth(34.0);
								}else if(item == 2){
									ItemStack truc = new ItemStack(Material.BOW, 1);
									ItemStack trucPlus = new ItemStack(Material.ARROW, 1);
									ItemMeta trucM = truc.getItemMeta();
									trucM.addEnchant(Enchantment.ARROW_INFINITE, 2, true);
									trucM.setDisplayName("§cFight§c§lBow");
									truc.setItemMeta(trucM);
									p.getInventory().addItem(truc);
									p.getInventory().addItem(trucPlus);
									p.setMaxHealth(16.0);
									p.setHealth(16.0);
								}else if(item == 3){
									ItemStack truc = new ItemStack(Material.DIAMOND_HOE, 1);
									ItemMeta trucM = truc.getItemMeta();
									trucM.setDisplayName("§cFight§c§lGun");
									truc.setItemMeta(trucM);
									p.getInventory().addItem(truc);
									Main.getInstance().life.put(p, 3);
									p.setMaxHealth(36.0);
									p.setHealth(36.0);
								}
								for(final Player pls : Bukkit.getOnlinePlayers()){
								task4 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){

									int titi = 5;
									@Override
									public void run() {
										titi--;
										if(titi == 4){
											pls.sendMessage("§7[§cPvP§c§lFight§7] Préparez-vous, on vous lâche bientôt §8!");
											
										}if(titi == 0){
											pls.sendMessage("§7[§cPvP§c§lFight§7] §cC'est parti §8!");
											GameState.setState(GameState.GAME);
											Bukkit.getScheduler().cancelTask(task);
											Locations.clearChat();
											pls.setLevel(0);

										}else if(titi > 0){
											pls.setLevel(titi);
										}
										
									}
									
								},20,20);
									
								}
								
							}else{
								p.sendMessage("§7[§c§lPvPFight§7] §cErreur ---> §7Il n'y a pas assez de joueurs pour commencer la partie §8!");
								
									
									task1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){
										int ti = 0;
										@Override
										public void run() {
											ti++;
											if(ti == 6){
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
									
									Bukkit.getScheduler().cancelTask(task);	
								}
							
									
							
						}
					}
					
					}
			},20,20);	
		}
	}
	@EventHandler
	public void quit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		e.setQuitMessage("");
		for(Player pl : Bukkit.getOnlinePlayers()){
			Title.sendActionBar(pl, "§c§l" + p.getName() + " §7à quitter §c§lPvPFight §7!");
		}
		if(Main.getInstance().playersList.contains(p)){
			Main.getInstance().playersList.remove(p);
			e.setQuitMessage(null);
		}
		PVPWinner.Check();
	}
}

			
