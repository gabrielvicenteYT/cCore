package cc.causalmc.ccore.commands.arguments.mod;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import cc.causalmc.ccore.utils.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TpPosCommand extends Command {

	public TpPosCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("tppos");
		this.addAlias("teleportpos");
		this.addAlias("teleportposition");
		this.addAlias("tpposition");
		this.setDescription("teleportpos Command");
		this.setSyntax("/tppos <x> <y> <z>");
		this.addRequirement(Requirement.MODERATOR);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (args.length < 3) {
				player.sendMessage(CC.translate("&cUsage: /tppos <x> <y> <z>"));
			} else {
				if (!NumberUtils.isInteger(args[0])) {
					player.sendMessage(CC.translate("&cNeeds to be a number."));
					return false;
				}

				if (!NumberUtils.isInteger(args[1])) {
					player.sendMessage(CC.translate("&cNeeds to be a number."));
					return false;
				}

				if (!NumberUtils.isInteger(args[2])) {
					player.sendMessage(CC.translate("&cNeeds to be a number."));
					return false;
				}

				int x = (int) Integer.parseInt(args[0]);
				int y = (int) Integer.parseInt(args[1]);
				int z = (int) Integer.parseInt(args[2]);

				Location loc = new Location(player.getWorld(), x, y, z);

				if (x > 3000000 || y > 1000 || z > 3000000 || x < -3000000 || y < -1000 || z < -3000000) {
					sender.sendMessage(CC.translate("&cMaximum coordinates are +/- 3000000 1000 3000000."));
					return false;
				}

				player.teleport(loc);

				return true;
			}
		}
		return false;
	}
}