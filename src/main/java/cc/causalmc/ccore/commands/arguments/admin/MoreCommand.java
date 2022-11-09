package cc.causalmc.ccore.commands.arguments.admin;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class MoreCommand extends Command {

	public MoreCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("more");
		this.setDescription("more Command");
		this.setSyntax("/more");
		this.addRequirement(Requirement.ADMIN);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if (sender instanceof Player){
			Player player = (Player) sender;

			ItemStack item = player.getItemInHand();

			if(item == null || item.getType() == Material.AIR) {
				player.sendMessage(CC.translate("&cYou need to have the item in your hand."));
				return false;
			}

			if(item.getAmount() >= 64) {
				player.sendMessage(CC.translate("&cYour item is already stacked."));
				return false;
			}

			item.setAmount(64);

			player.updateInventory();

			player.sendMessage(CC.translate("&dYou have stacked your item."));
		}
		return false;
	}

}