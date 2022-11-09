package cc.causalmc.ccore.commands.arguments.op;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class PermaCommand extends Command {

	public PermaCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("perma");
		this.addAlias("blacklist");
		this.setDescription("perma Command");
		this.setSyntax("/perma <name/uuid>");
		this.addRequirement(Requirement.OP);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {

		if(args.length == 1){
			Bukkit.dispatchCommand(sender, "ipban "+ args[0] + " §cBlacklisted -s");
			return true;
		}else{
			sender.sendMessage(CC.translate("&cUsage: /perma <player/uuid>"));
		}
		return false;
	}
}