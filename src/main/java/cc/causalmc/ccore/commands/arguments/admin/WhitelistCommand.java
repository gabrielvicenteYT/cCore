package cc.causalmc.ccore.commands.arguments.admin;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import net.minecraft.server.v1_8_R3.WhiteList;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class WhitelistCommand extends Command {

	public WhitelistCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("wl");
		this.addAlias("whiteList");
		this.setDescription("Test Command");
		this.setSyntax("/wl <on/off/add/rem> [player]");
		this.addRequirement(Requirement.ADMIN);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {





		return false;
	}

}