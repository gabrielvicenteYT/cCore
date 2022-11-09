package cc.causalmc.ccore.commands.arguments.admin;

import cc.causalmc.ccore.CCore;
import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import cc.causalmc.ccore.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class GrantCommand extends Command implements Listener {

	public GrantCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("grant");
		this.addAlias("setrank");
		this.setDescription("grant Command");
		this.setSyntax("/grant <player>");
		this.addRequirement(Requirement.ADMIN);
	}


	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage(CC.translate("&cUsage: /grant <player>"));
			} else {
			Player target = Bukkit.getPlayer(args[0]);

				if (CC.checkOffline(sender, args[0])) {
					return false;
				}
				Inventory inv = Bukkit.createInventory(null, 18, "§dGrant menu");

				ItemStack owner = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta meta = (LeatherArmorMeta) owner.getItemMeta();
				meta.setColor(Color.fromRGB(170, 0, 0));
				meta.setDisplayName(CC.translate("&4Owner"));
				owner.setItemMeta(meta);

				ItemStack dev = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta meta99 = (LeatherArmorMeta) dev.getItemMeta();
				meta99.setColor(Color.fromRGB(255, 170, 0));
				meta99.setDisplayName(CC.translate("&6&oDev"));
				dev.setItemMeta(meta99);

				ItemStack man = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta meta2 = (LeatherArmorMeta) man.getItemMeta();
				meta2.setColor(Color.fromRGB(255, 170, 0));
				meta2.setDisplayName(CC.translate("&6Manager"));
				man.setItemMeta(meta2);

				ItemStack sradmin = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta meta3 = (LeatherArmorMeta) sradmin.getItemMeta();
				meta3.setColor(Color.fromRGB(255, 85, 85));
				meta3.setDisplayName(CC.translate("&c&oSrAdmin"));
				sradmin.setItemMeta(meta3);

				ItemStack admin = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta meta66 = (LeatherArmorMeta) sradmin.getItemMeta();
				meta66.setColor(Color.fromRGB(255, 85, 85));
				meta66.setDisplayName(CC.translate("&cAdmin"));
				admin.setItemMeta(meta66);

				ItemStack mod = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta meta4 = (LeatherArmorMeta) mod.getItemMeta();
				meta4.setColor(Color.fromRGB(170, 0, 170));
				meta4.setDisplayName(CC.translate("&5Mod"));
				mod.setItemMeta(meta4);
				ItemStack srmod = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta meta45 = (LeatherArmorMeta) mod.getItemMeta();
				meta45.setColor(Color.fromRGB(170, 0, 170));
				meta45.setDisplayName(CC.translate("&5&oSrMod"));
				srmod.setItemMeta(meta45);

				ItemStack trial = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta meta5 = (LeatherArmorMeta) trial.getItemMeta();
				meta5.setColor(Color.fromRGB(255, 255, 85));
				meta5.setDisplayName(CC.translate("&eTrial"));
				trial.setItemMeta(meta5);

				ItemStack friend = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta meta6 = (LeatherArmorMeta) friend.getItemMeta();
				meta6.setColor(Color.fromRGB(85, 255, 255));
				meta6.setDisplayName(CC.translate("&bFriend"));
				friend.setItemMeta(meta6);

				ItemStack famous = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta meta7 = (LeatherArmorMeta) famous.getItemMeta();
				meta7.setColor(Color.fromRGB(255, 85, 255));
				meta7.setDisplayName(CC.translate("&d&oFamous"));
				famous.setItemMeta(meta7);

				ItemStack media = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta meta8 = (LeatherArmorMeta) media.getItemMeta();
				meta8.setColor(Color.fromRGB(255, 85, 255));
				meta8.setDisplayName(CC.translate("&dMedia"));
				media.setItemMeta(meta8);

				ItemStack elitep = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta meta9 = (LeatherArmorMeta) elitep.getItemMeta();
				meta9.setColor(Color.fromRGB(255, 170, 0));
				meta9.setDisplayName(CC.translate("&aElite+"));
				elitep.setItemMeta(meta9);

				ItemStack advanced = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta meta10 = (LeatherArmorMeta) advanced.getItemMeta();
				meta10.setColor(Color.fromRGB(255, 170, 0));
				meta10.setDisplayName(CC.translate("&6Advanced"));
				advanced.setItemMeta(meta10);

				ItemStack elite = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta meta11 = (LeatherArmorMeta) elite.getItemMeta();
				meta11.setColor(Color.fromRGB(85, 255, 85));
				meta11.setDisplayName(CC.translate("&bElite"));
				elite.setItemMeta(meta11);

				ItemStack elitepter = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta meta12 = (LeatherArmorMeta) elitepter.getItemMeta();
				meta12.setColor(Color.fromRGB(255, 255, 255));
				meta12.setDisplayName(CC.translate("&fStarter"));
				elitepter.setItemMeta(meta12);

				ItemStack joueur = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta meta13 = (LeatherArmorMeta) joueur.getItemMeta();
				meta13.setColor(Color.fromRGB(85, 255, 85));
				meta13.setDisplayName(CC.translate("&aDefault"));
				joueur.setItemMeta(meta13);



				inv.addItem(owner);
				inv.addItem(dev);
				inv.addItem(man);
				inv.addItem(sradmin);
				inv.addItem(admin);
				inv.addItem(srmod);
				inv.addItem(mod);
				inv.addItem(trial);
				inv.addItem(famous);
				inv.addItem(media);
				inv.addItem(friend);
				inv.addItem(elitep);
				inv.addItem(elite);
				inv.addItem(advanced);
				inv.addItem(elitepter);
				inv.addItem(joueur);


				player.openInventory(inv);
				CCore.getInstance().grantMap.put(player.getName(), target.getName());

				return true;
			}
			return false;
		}
		return false;
	}


	@EventHandler
	public void grantInteractEvent(InventoryClickEvent e){
		Inventory inventory = e.getInventory();
		Player player = (Player) e.getWhoClicked();


		if (inventory.getName().equalsIgnoreCase("§dGrant menu")) {

			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&4Owner"))) {CCore.getInstance().rank = "owner ";}
			else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&6&oDev"))) {CCore.getInstance().rank = "dev ";}
			else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&6Manager"))) {CCore.getInstance().rank = "manager ";}
			else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&5&oSrMod"))) {CCore.getInstance().rank = "srmod ";}
			else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&5Mod"))) {CCore.getInstance().rank = "mod ";}
			else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&eTrial"))) {CCore.getInstance().rank = "trial ";}
			else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&c&oSrAdmin"))) {CCore.getInstance().rank = "sradmin ";}
			else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&cAdmin"))) {CCore.getInstance().rank = "admin ";}
			else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&d&oFamous"))) {CCore.getInstance().rank = "famous ";}
			else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&dMedia"))) {CCore.getInstance().rank = "media ";}
			else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&bFriend"))) {CCore.getInstance().rank = "friend ";}
			else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&aElite+"))) {CCore.getInstance().rank = "elite+ ";}
			else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&bElite"))) {CCore.getInstance().rank = "elite ";}
			else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&6Advanced"))) {CCore.getInstance().rank = "advanced ";}
			else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&fStarter"))) {CCore.getInstance().rank = "starter ";}
			else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&aDefault"))) {CCore.getInstance().rank = "default ";}

			e.setCancelled(true);
			player.closeInventory();

			Inventory inv = Bukkit.createInventory(null, 9, "§dGrant time");

			ItemStack cinq = new ItemBuilder(Material.WATCH).name("5m").build();
			ItemStack trentre = new ItemBuilder(Material.WATCH).name("30m").build();
			ItemStack heure = new ItemBuilder(Material.WATCH).name("1h").build();
			ItemStack jour = new ItemBuilder(Material.WATCH).name("1d").build();
			ItemStack semaine = new ItemBuilder(Material.WATCH).name("7d").build();
			ItemStack dsemaine = new ItemBuilder(Material.WATCH).name("14d").build();
			ItemStack mois = new ItemBuilder(Material.WATCH).name("30d").build();
			ItemStack perm = new ItemBuilder(Material.WATCH).name("Permanent").build();



			inv.addItem(cinq);
			inv.addItem(trentre);
			inv.addItem(heure);
			inv.addItem(jour);
			inv.addItem(semaine);
			inv.addItem(dsemaine);
			inv.addItem(mois);
			inv.addItem(perm);

			player.openInventory(inv);

		}
		if (inventory.getName().equalsIgnoreCase("§dGrant time")) {

			CCore.getInstance().time = e.getCurrentItem().getItemMeta().getDisplayName();
			e.setCancelled(true);
			player.closeInventory();

			Inventory inv = Bukkit.createInventory(null, 9, "§dGrant Confimation");

			ItemStack no = new ItemBuilder(Material.STAINED_CLAY).name("§cNo").durability((short) 14).build();
			ItemStack yes = new ItemBuilder(Material.STAINED_CLAY).name("§aYes").durability((short) 5).build();

			inv.setItem(3, no);
			inv.setItem(5, yes);

			player.openInventory(inv);
		}
		if (inventory.getName().equalsIgnoreCase("§dGrant Confimation")) {
			if (e.getCurrentItem().getType() == Material.STAINED_CLAY) {
				e.setCancelled(true);
				player.closeInventory();

				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cNo")) {
					CCore.getInstance().grantMap.remove(player);
					return;
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aYes")) {
					String target = CCore.getInstance().grantMap.get(player.getName());

					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String cmd;
					if(CCore.getInstance().time.equalsIgnoreCase("Permanent")) {
						cmd = ("lp user " + target + " group set " + CCore.getInstance().rank);
					}
					else {
						cmd = ("lp user " + target + " group addtemp " + CCore.getInstance().rank + CCore.getInstance().time);
					}

					Bukkit.dispatchCommand(console, cmd);
					CCore.getInstance().grantMap.remove(player);
				}
			}
		}
	}
}

