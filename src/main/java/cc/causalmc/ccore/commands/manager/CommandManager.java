package cc.causalmc.ccore.commands.manager;

import cc.causalmc.ccore.commands.arguments.op.*;
import cc.causalmc.ccore.commands.arguments.mod.*;
import cc.causalmc.ccore.commands.arguments.player.*;
import cc.causalmc.ccore.commands.arguments.admin.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


public class CommandManager implements CommandExecutor {
	private ArrayList<Command> commands;

	private JavaPlugin plugin;

	public CommandManager(JavaPlugin Core) {
		plugin = Core;
		commands = new ArrayList<>();
		addCommand(new StoreCommand(plugin));
	//	addCommand(new NickCommand(plugin));
		addCommand(new TpAllCommand(plugin));
		addCommand(new TpHereCommand(plugin));
		addCommand(new TpCommand(plugin));
		addCommand(new TpPosCommand(plugin));
		addCommand(new CommandTest(plugin));
		addCommand(new DiscordCommand(plugin));
		addCommand(new PingCommand(plugin));
		addCommand(new ClearCommand(plugin));
		addCommand(new EnchantCommand(plugin));
		addCommand(new GrantCommand(plugin));
		addCommand(new GiveCommand(plugin));
		addCommand(new InvseeCommand(plugin));
		addCommand(new LagCommand(plugin));
		addCommand(new RenameCommand(plugin));
		addCommand(new KillCommand(plugin));
		addCommand(new KillAllCommand(plugin));
		addCommand(new HealCommand(plugin));
		addCommand(new KickAllCommand(plugin));
		addCommand(new FeedCommand(plugin));
		addCommand(new GmaCommand(plugin));
		addCommand(new GmcCommand(plugin));
		addCommand(new GmsCommand(plugin));
		addCommand(new GmspCommand(plugin));
		addCommand(new GamemodeCommand(plugin));
		addCommand(new CoinsCommand(plugin));
		addCommand(new CreditsCommand(plugin));
		addCommand(new TagsCommand(plugin));
		addCommand(new MoreCommand(plugin));
		addCommand(new CosmeticsCommand(plugin));
		addCommand(new VerCommand(plugin));
		//addCommand(new WhitelistCommand(plugin));
		//addCommand(new PermaCommand(plugin));
		/*addCommand(new NicklistCommand(plugin));
		addCommand(new UnnickCommand(plugin));
		addCommand(new RealnameCommand(plugin));*/
		registerCommands();
	}

	public void addCommand(Command c) {
		commands.add(c);
	}

	public ArrayList<Command> getCommands() {
		return commands;
	}

	public void registerCommands() {
		for (Command c : this.getCommands()) {
			for (String l : c.getAliases()) {
				plugin.getCommand(l).setExecutor(this);
			}
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
		for (Command c : getCommands()) {
			if (c.getAliases().contains(label.toLowerCase())) {
				if (!meetsRequirements(c, sender)) {
					sender.sendMessage(
							ChatColor.translateAlternateColorCodes('&', "&cNo perms."));
					return false;
				}
				try {
					c.execute(sender, args);
				} catch (Exception e) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&cAn error occured, look at console or contacts the admins."));
					e.printStackTrace();
				}
				return true;
			}
		}
		return false;
	}

	public boolean meetsRequirements(Command c, CommandSender s) {
		for (Requirement r : c.getRequirements()) {
			if (!c.hasRequirement(s, r)) {
				return false;
			}
		}
		return true;
	}

	public enum Requirement {
		PLAYER, OP, MODERATOR, ADMIN;
	}

}