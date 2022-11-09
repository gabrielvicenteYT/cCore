package cc.causalmc.ccore.commands.arguments.admin;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TpAllCommand extends Command {

	public TpAllCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("tpall");
		this.addAlias("teleportall");
		this.setDescription("tpall Command");
		this.setSyntax("/tpall [player]");
		this.addRequirement(Requirement.ADMIN);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if(args.length == 0) {
				for(Player online : Bukkit.getOnlinePlayers()) {
					online.teleport(player.getLocation());
				}
			}
			if(args.length > 0){
				Player target = Bukkit.getPlayer(args[0]);

				if (CC.checkOffline(sender, args[0])) {

					return false;
				}
				for(Player online2 : Bukkit.getOnlinePlayers()) {
					online2.teleport(target.getLocation());
				}
			}

			return true;
		}
		return false;
	}
}