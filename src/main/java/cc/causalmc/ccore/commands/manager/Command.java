package cc.causalmc.ccore.commands.manager;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public abstract class Command {

	protected JavaPlugin pl;

	private ArrayList<String> aliases = new ArrayList<String>();
	private ArrayList<CommandManager.Requirement> requirements = new ArrayList<CommandManager.Requirement>();
	private String syntax;
	private String description;

	public Command(JavaPlugin m) {
		pl = m;
	}

	public ArrayList<String> getAliases() {
		return aliases;
	}

	public void setAliases(ArrayList<String> aliases) {
		this.aliases = aliases;
	}

	public String getSyntax() {
		return syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	public void addAlias(String a) {
		aliases.add(a);
	}

	public void addRequirement(CommandManager.Requirement r) {
		requirements.add(r);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<CommandManager.Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(ArrayList<CommandManager.Requirement> requirements) {
		this.requirements = requirements;
	}

	public abstract boolean execute(CommandSender sender, String[] args) throws Exception;

	public boolean hasRequirement(CommandSender s, CommandManager.Requirement r) {
		switch (r) {
		case PLAYER:
			if (!(s instanceof Player)) {
				return false;
			}
			break;
		case OP:
			if (s instanceof Player) {
				if (!s.hasPermission("causal.op")) {
					return false;
				}
			}
			break;
		case MODERATOR:
			if (s instanceof Player) {
				if (!s.hasPermission("causal.mod")) {
					return false;
				}
			}
			break;
		case ADMIN:
			if (s instanceof Player) {
				if (!s.hasPermission("causal.admin")) {
					return false;
				}
			}
			break;
		}
		return true;
	}

}