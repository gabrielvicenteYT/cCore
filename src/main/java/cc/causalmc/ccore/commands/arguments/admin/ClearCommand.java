package cc.causalmc.ccore.commands.arguments.admin;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ClearCommand extends Command {

	public ClearCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("clear");
		this.setDescription("clear Command");
		this.setSyntax("/clear [player]");
		this.addRequirement(Requirement.ADMIN);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if(sender instanceof Player) {
			Player player = (Player) sender;

			if(args.length == 0) {
				this.clearPlayer(player);

				player.sendMessage(CC.translate("&dYou have cleared your inventory."));
			} else {
				if(player.hasPermission("causal.admin")) {
					Player target = Bukkit.getPlayer(args[0]);

					if (CC.checkOffline(sender, args[0])) {

						return false;
					}

					this.clearPlayer(target);

					target.sendMessage(CC.translate("&dYour inventory has been cleared by &5" + player.getDisplayName() + "&d."));

				} else {
					player.sendMessage(CC.NO_PERMISSION);
				}
			}

			return true;
		}

		if(args.length == 0) {
			sender.sendMessage(CC.translate("&cUsage: /clear <player>"));
		} else {
			Player target = Bukkit.getPlayer(args[0]);

			if (CC.checkOffline(sender, args[0])) {

				return false;
			}

			this.clearPlayer(target);

			target.sendMessage(CC.translate("&dYour inventory has been cleared by " + CC.CONSOLE + "&d."));
			sender.sendMessage(CC.translate("&dYou have cleared inventory of " + target.getDisplayName() + "&d."));

			return true;
		}
		return false;
	}
	public void clearPlayer(Player player) {
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
	}
}