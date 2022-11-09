package cc.causalmc.ccore.commands.arguments.mod;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class KillCommand extends Command {

	public KillCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("kill");
		this.setDescription("kill Command");
		this.setSyntax("/kill <player>");
		this.addRequirement(Requirement.MODERATOR);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if(sender instanceof Player) {
			Player player = (Player) sender;

			if(args.length == 0) {
				sender.sendMessage(CC.translate("&cUsage: /kill <player>"));
			} else {
				Player target = Bukkit.getPlayer(args[0]);

				if (CC.checkOffline(sender, args[0])) {

					return false;
				}

				this.killPlayer(target);

				target.sendMessage(CC.translate("&dYou have been killed by &5" + player.getName()));

			}

			return true;
		}

		if(args.length == 0) {
			sender.sendMessage(CC.translate("&cUsage: /kill <player>"));
		} else {
			Player target = Bukkit.getPlayer(args[0]);

			if (CC.checkOffline(sender, args[0])) {

				return false;
			}

			this.killPlayer(target);

			target.sendMessage(CC.translate("&dYou have been killed by &5" + CC.CONSOLE));
			sender.sendMessage(CC.translate("&eYou have killed " + target.getDisplayName() + "&e."));

			return true;
		}
		return false;
	}

	public void killPlayer(Player player) {
		player.setHealth(0.0);
	}
}