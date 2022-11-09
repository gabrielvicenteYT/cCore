package cc.causalmc.ccore.commands.arguments.mod;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TpHereCommand extends Command {

	public TpHereCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("tphere");
		this.addAlias("teleporthere");
		this.addAlias("teleporttome");
		this.addAlias("tptome");
		this.setDescription("tphere Command");
		this.setSyntax("/tphere <player>");
		this.addRequirement(Requirement.MODERATOR);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if(args.length == 0) {
				player.sendMessage(CC.translate("&cUsage: /tphere <player>"));
			} else {
				Player target = Bukkit.getPlayer(args[0]);

				if (CC.checkOffline(sender, args[0])) {

					return false;
				}
				target.teleport(player.getLocation());
			}
			return true;
		}
		return false;
	}
}