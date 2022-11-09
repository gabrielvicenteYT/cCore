package cc.causalmc.ccore.commands.arguments.mod;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TpCommand extends Command {

	public TpCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("tp");
		this.addAlias("teleport");
		this.addAlias("teleportto");
		this.addAlias("tpto");
		this.setDescription("teleport Command");
		this.setSyntax("/tp <player>");
		this.addRequirement(Requirement.MODERATOR);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if(args.length == 0) {
				player.sendMessage(CC.translate("&cUsage: /tp <player>"));
			} else {
				Player target = Bukkit.getPlayer(args[0]);

				if (CC.checkOffline(sender, args[0])) {
					return false;
				}
				player.teleport(target.getLocation());
			}
			return true;
		}
		return false;
	}

}