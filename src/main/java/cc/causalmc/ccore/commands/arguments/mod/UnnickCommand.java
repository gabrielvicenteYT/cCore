package cc.causalmc.ccore.commands.arguments.mod;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.haoshoku.nick.api.NickAPI;

public class UnnickCommand extends Command {

	public UnnickCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("unnick");
		this.setDescription("moderator unnick Command");
		this.setSyntax("/unnick <pseudo>");
		this.addRequirement(Requirement.MODERATOR);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage(CC.translate("&cMauvais usage: /unnick <pseudo>"));
				return false;
			}
			String nickedPlayer = NickAPI.getOriginalName(Bukkit.getPlayer(args[0]));
			NickAPI.resetNick(Bukkit.getPlayer(nickedPlayer));
			player.sendMessage(CC.translate("&aLe nick de ce joueur a été reset"));
			return true;
		}
		return false;
	}

}