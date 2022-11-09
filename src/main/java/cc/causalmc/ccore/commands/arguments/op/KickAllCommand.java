package cc.causalmc.ccore.commands.arguments.op;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class KickAllCommand extends Command {

	public KickAllCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("kickall");
		this.setDescription("kickall Command");
		this.setSyntax("/kickall <reason>");
		this.addRequirement(Requirement.OP);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if(sender instanceof Player) {
			Player player = (Player) sender;

			if(args.length == 0) {
				sender.sendMessage(CC.translate("&cUsage: /kickall <reason>"));
			} else {
				StringBuilder message = new StringBuilder();
				for(int i = 0; i < args.length; i++) {
					message.append(args[i]).append(" ");
				}

				for(Player online : Bukkit.getOnlinePlayers()) {
					if(!online.hasPermission("causal.mod")) {
						online.kickPlayer(CC.translate(message.toString()));
					}
				}


				return true;
			}

		}

		if(args.length == 0) {
			sender.sendMessage(CC.translate("&cUsage: /kickall <reason>"));
		} else {
			StringBuilder message = new StringBuilder();
			for(int i = 0; i < args.length; i++) {
				message.append(args[i]).append(" ");
			}

			for(Player online : Bukkit.getOnlinePlayers()) {
				if(!online.hasPermission("causal.mod")) {
					online.kickPlayer(CC.translate(message.toString()));
				}
			}

			return true;
		}
		return false;
	}



}