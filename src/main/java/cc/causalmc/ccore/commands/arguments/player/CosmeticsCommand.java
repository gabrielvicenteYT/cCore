package cc.causalmc.ccore.commands.arguments.player;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.menus.CosmeticsMenu;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class CosmeticsCommand extends Command implements Listener {

	public CosmeticsCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("cos");
		this.addAlias("cosmetics");
		this.setDescription("Cosmetics Command");
		this.setSyntax("/cos");
		this.addRequirement(Requirement.PLAYER);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if(sender instanceof Player){
			Player player = (Player) sender;
			CosmeticsMenu.open(player);
			return true;
		}
		return false;
	}

	@EventHandler
	public void onCosmeticsInteract(InventoryClickEvent e) {
		if(CosmeticsMenu.cosMenuOpened.contains(e.getWhoClicked().getUniqueId())) {
			Player player = (Player) e.getWhoClicked();
			e.setCancelled(true);
			if(e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && !Objects.equals(e.getCurrentItem().getItemMeta().getDisplayName(), " ")){
				String name = e.getCurrentItem().getItemMeta().getDisplayName();
				if(name.equalsIgnoreCase(CC.translate("&bTags"))){
					player.closeInventory();
					player.performCommand("tags");
				}
			}
		}
	}


	@EventHandler
	public void onCosmeticsMenuClose(InventoryCloseEvent e){
		if(CosmeticsMenu.cosMenuOpened.contains(e.getPlayer().getUniqueId())) CosmeticsMenu.cosMenuOpened.remove(e.getPlayer().getUniqueId());
	}
}