package cc.causalmc.ccore.commands.arguments.mod;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GamemodeCommand extends Command {

	public GamemodeCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("gamemode");
		this.addAlias("gm");
		this.setDescription("gamemode Command");
		this.setSyntax("/gm <c|s|a|sp> [player]");
		this.addRequirement(Requirement.MODERATOR);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (args.length == 0) {
				player.sendMessage(CC.translate("&cUsage: /gamemode <C|S|A|SP> [player]"));
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
					player.setGameMode(GameMode.CREATIVE);
					return true;
				} else if (args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
					player.setGameMode(GameMode.SURVIVAL);
					return true;
				} else if (args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
					player.setGameMode(GameMode.ADVENTURE);
					return true;
				} else if (args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectateur") || args[0].equalsIgnoreCase("spec")) {
					player.setGameMode(GameMode.SPECTATOR);
					return true;
				}
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
					Player target = Bukkit.getPlayer(args[1]);

					if (CC.checkOffline(sender, args[0])) {

						return false;
					}
					target.setGameMode(GameMode.CREATIVE);
					target.sendMessage(CC.translate("&dYour gamemode has been updated to &5Creative &dby &5" + player.getDisplayName() + "&d."));
					return true;
				} else if (args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
					Player target = Bukkit.getPlayer(args[1]);

					if (CC.checkOffline(sender, args[0])) {

						return false;
					}
					target.setGameMode(GameMode.SURVIVAL);
					target.sendMessage(CC.translate("&dYour gamemode has been updated to &5Survival &dby &5" + player.getDisplayName() + "&d."));
					return true;
				} else if (args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
					Player target = Bukkit.getPlayer(args[1]);

					if (CC.checkOffline(sender, args[0])) {

						return false;
					}
					target.setGameMode(GameMode.ADVENTURE);
					target.sendMessage(CC.translate("&dYour gamemode has been updated to &5Adventure &dby &5" + player.getDisplayName() + "&d."));
					return true;
				} else if (args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("spec")) {
					Player target = Bukkit.getPlayer(args[1]);

					if (CC.checkOffline(sender, args[0])) {

						return false;
					}
					target.setGameMode(GameMode.SPECTATOR);
					target.sendMessage(CC.translate("&dYour gamemode has been updated to &5Spectator &dby &5" + player.getDisplayName() + "&d."));
					return true;
				} else {
					player.sendMessage(CC.translate("&cUsage: /gamemode <C|S|A|SP> <player>"));
					return false;
				}
			}
		}
		return false;
	}
}