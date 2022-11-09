package cc.causalmc.ccore.commands.arguments.op;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandTest extends Command {

	public CommandTest(JavaPlugin Core) {
		super(Core);
		this.addAlias("Test");
		this.setDescription("Test Command");
		this.setSyntax("/test");
		this.addRequirement(Requirement.OP);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		sender.sendMessage("Hello Admin Or Console");
		return false;
	}

}