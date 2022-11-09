package cc.causalmc.ccore.commands.arguments.mod;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class RenameCommand extends Command {

	public RenameCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("rename");
		this.setDescription("rename Command");
		this.setSyntax("/rename <name>");
		this.addRequirement(Requirement.MODERATOR);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if(sender instanceof Player) {
			Player player = (Player) sender;

			if(args.length == 0) {
				sender.sendMessage(CC.translate("&cUsage: /rename <name>"));
			} else {
				ItemStack item = player.getItemInHand();

				if(item == null || item.getType() == Material.AIR) {
					player.sendMessage(CC.translate("&cYou need to have the item in your hand."));
					return false;
				}

				StringBuilder name = new StringBuilder();

				for(int i = 0; i < args.length; i++) {
					name.append(args[i]).append(" ");
				}

				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(CC.translate(name.toString()));

				item.setItemMeta(meta);
				player.updateInventory();

				player.sendMessage(CC.translate("&dYou renamed that item in " + name + "&d."));
			}
		}
		return false;
	}

}