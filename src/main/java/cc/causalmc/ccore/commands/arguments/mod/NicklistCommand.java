package cc.causalmc.ccore.commands.arguments.mod;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.haoshoku.nick.api.NickAPI;

import java.util.Map;
import java.util.UUID;

public class NicklistCommand extends Command {

	public NicklistCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("nicklist");
		this.addAlias("disguiselist");
		this.setDescription("nicklist Command");
		this.setSyntax("/nicklist");
		this.addRequirement(Requirement.MODERATOR);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage(CC.CHAT_BAR);
			if(NickAPI.getNickedPlayers().isEmpty()) {
				player.sendMessage("§cPas de joueurs nick");
			} else {
				for (Map.Entry<UUID, String> p : NickAPI.getNickedPlayers().entrySet()) {
					player.sendMessage(CC.translate("&8» &d" + p.getValue() + "&7(" + NickAPI.getOriginalName(Bukkit.getPlayer(p.getKey())) + ")"));
				}
			}
			player.sendMessage(CC.CHAT_BAR);
			return true;
		}
		return false;
	}

}