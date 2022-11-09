package cc.causalmc.ccore.commands.arguments.op;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FeedCommand extends Command {

	public FeedCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("feed");
		this.setDescription("feed Command");
		this.setSyntax("/feed <player|all>");
		this.addRequirement(CommandManager.Requirement.OP);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if(sender instanceof Player) {
			Player player = (Player) sender;

			if(args.length == 0) {
				this.feedPlayer(player);

				player.sendMessage(CC.translate("&dYou have been &5fed&d."));
			} else {
				if(args[0].equalsIgnoreCase("all")) {

					for(Player online : Bukkit.getOnlinePlayers()) {
						this.feedPlayer(online);
						online.sendMessage(CC.translate("&dYou have been &5fed&d by &5" + player.getName() + "&d."));
					}
					return true;

				} else {
					Player target = Bukkit.getPlayer(args[0]);

					if (CC.checkOffline(sender, args[0])) {
						return false;
					}

					this.feedPlayer(target);
					sender.sendMessage(CC.translate("&dYou have &5fed " + target.getDisplayName() + "&d."));
					target.sendMessage(CC.translate("&dYou have been &5fed&d by &5" + player.getName() + "&d."));

					return true;
				}
			}

			return false;
		}

		if(args.length == 0) {
			sender.sendMessage(CC.translate("&cUsage: /feed <player|all>"));
		} else {
			if(args[0].equalsIgnoreCase("all")) {
				for(Player online : Bukkit.getOnlinePlayers()) {
					this.feedPlayer(online);
					online.sendMessage(CC.translate("&dYou have been &5fed&d by " + CC.CONSOLE + "&d."));
				}

			} else {
				Player target = Bukkit.getPlayer(args[0]);

				if (CC.checkOffline(sender, args[0])) {

					return false;
				}

				this.feedPlayer(target);

				target.sendMessage(CC.translate("&dYou have been &5fed&d by " + CC.CONSOLE + "&d."));
				sender.sendMessage(CC.translate("&dYou have &5fed " + target.getDisplayName() + "&d."));

				return true;
			}
		}
		return false;
	}

	public void feedPlayer(Player player) {
		player.setFoodLevel(20);
		player.setSaturation(10);
	}

}