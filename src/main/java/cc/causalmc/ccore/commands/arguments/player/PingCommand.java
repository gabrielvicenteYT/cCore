package cc.causalmc.ccore.commands.arguments.player;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import cc.causalmc.ccore.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PingCommand extends Command {

	public PingCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("ping");
		this.addAlias("ms");
		this.addAlias("latence");
		this.setDescription("Ping Command");
		this.setSyntax("/ping [player]");
		this.addRequirement(Requirement.PLAYER);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage(CC.translate("&dYour Ping: &5" + Utilities.getPing(player) + "ms&d."));
			} else {
				Player target = Bukkit.getPlayer(args[0]);

				if (CC.checkOffline(sender, args[0])) {

					return false;
				}
				player.sendMessage(CC.translate(
						"&5" + target.getName() + "&d's ping is &5" + Utilities.getPing(target) + "ms&d. &7(&f" + (Math.max(Utilities.getPing(player), Utilities.getPing(target)) - Math.min(Utilities.getPing(player), Utilities.getPing(target))) + "ms&7)"
				));
				return true;
			}
		}
	return false;
	}
}