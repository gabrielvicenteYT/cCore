package cc.causalmc.ccore.commands.arguments.mod;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GmcCommand extends Command {

	public GmcCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("gmc");
		this.setDescription("Gamemode creative Command");
		this.setSyntax("/gmc [player]");
		this.addRequirement(Requirement.MODERATOR);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if(sender instanceof Player) {
			Player player = (Player) sender;

			if(args.length == 0) {
				player.setGameMode(GameMode.CREATIVE);
			} else {
				Player target = Bukkit.getPlayer(args[0]);

				if (CC.checkOffline(sender, args[0])) {
					return false;
				}
				target.setGameMode(GameMode.CREATIVE);
				target.sendMessage(CC.translate("&dYour gamemode has been updated to &5Creative &dby &5" + player.getDisplayName()+ "&d."));
				return true;
			}

			return true;
		}

		if(args.length == 0) {
			sender.sendMessage(CC.translate("&cUsage: /gmc <player>"));
			return false;
		} else {
			Player target = Bukkit.getPlayer(args[0]);

			if (CC.checkOffline(sender, args[0])) {

				return false;
			}

			target.setGameMode(GameMode.CREATIVE);

			target.sendMessage(CC.translate("&dYour gamemode has been updated to &5Creative &dby &5 " + CC.CONSOLE+ "&b."));
			sender.sendMessage(CC.translate("&eYou have updated gamemode of " + target.getDisplayName() + " &eto &dCreative&e."));
		}
		return false;
	}

}