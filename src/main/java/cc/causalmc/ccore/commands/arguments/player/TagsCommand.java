package cc.causalmc.ccore.commands.arguments.player;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.menus.BasicBuyInventory;
import cc.causalmc.ccore.menus.TagMenu;
import cc.causalmc.ccore.backend.CausalPlayer;
import cc.causalmc.ccore.tags.Tags;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class TagsCommand extends Command implements Listener {

	public TagsCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("tags");
		this.addAlias("prefix");
		this.setDescription("tags Command");
		this.setSyntax("/tags");
		this.addRequirement(Requirement.PLAYER);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			TagMenu.open(player);
			return true;
		}
		return false;
	}


	@EventHandler
	public void onTagsInteract(InventoryClickEvent e) {
		if(TagMenu.tagMenuOpened.contains(e.getWhoClicked().getUniqueId())) {
			Player player = (Player) e.getWhoClicked();
			e.setCancelled(true);
			if(e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && !Objects.equals(e.getCurrentItem().getItemMeta().getDisplayName(), " ")){
				String name = e.getCurrentItem().getItemMeta().getDisplayName().substring(2);
				if(name.equalsIgnoreCase(CC.translate("&cReset"))){
					player.closeInventory();
					CausalPlayer.getPlayerInfos(player).resetTag();
				}else if(player.hasPermission(("causal.tags." + name.toLowerCase()))) {
					player.closeInventory();
					CausalPlayer.getPlayerInfos(player).setTag(name);
				} else{
					player.closeInventory();
					BasicBuyInventory.Buy(player.getUniqueId(), Objects.requireNonNull(Tags.getTagByName(name)).getPrice(), 0f, "causal.tags."+name.toLowerCase());
				}
			}
		}
	}

	@EventHandler
	public void onTagMenuClose(InventoryCloseEvent e){
		if(TagMenu.tagMenuOpened.contains(e.getPlayer().getUniqueId())) TagMenu.tagMenuOpened.remove(e.getPlayer().getUniqueId());
		if(TagMenu.tagConfirmMenuOpened.contains(e.getPlayer().getUniqueId())) TagMenu.tagConfirmMenuOpened.remove(e.getPlayer().getUniqueId());
	}
}