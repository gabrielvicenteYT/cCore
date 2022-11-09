package cc.causalmc.ccore.commands.arguments.player;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class VerCommand extends Command {

	public VerCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("ver");
		this.addAlias("icanhasbukkit");
		this.addAlias("version");
		this.addAlias("about");
		this.setDescription("Test Command");
		this.setSyntax("/ver");
		this.addRequirement(Requirement.PLAYER);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		sender.sendMessage(CC.translate("&dThis server is running &5cSpigot &dversion &51.6&d. &7(&fApi version 1.8.8-R0.1&7)"));
		return false;
	}

}