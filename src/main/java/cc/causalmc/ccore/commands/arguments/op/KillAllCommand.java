package cc.causalmc.ccore.commands.arguments.op;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.bukkit.plugin.java.JavaPlugin;

public class KillAllCommand extends Command {

	public KillAllCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("killall");
		this.setDescription("killall Command");
		this.setSyntax("/killall <all|mobs|animals|items>");
		this.addRequirement(Requirement.OP);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if (args.length == 0) {
			sender.sendMessage(CC.translate("&cUsage: /killall <all|mobs|animals|items>"));
		} else {
			if (args[0].equalsIgnoreCase("all")) {
				int total = 0;

				for (World world : Bukkit.getWorlds()) {
					for (Entity entity : world.getEntities()) {
						if (entity instanceof Monster || entity instanceof Animals || entity instanceof Item) {
							entity.remove();
							total++;
						}
					}
				}

				sender.sendMessage(CC.translate("&dYou killed all the Entities"));
				sender.sendMessage(CC.translate("&dTotal&7: &5" + total));
				return true;
			} else if (args[0].equalsIgnoreCase("mobs") || args[0].equalsIgnoreCase("mob")) {
				int total = 0;

				for (World world : Bukkit.getWorlds()) {
					for (Entity entity : world.getEntities()) {
						if (entity instanceof Monster) {
							entity.remove();
							total++;
						}
					}
				}

				sender.sendMessage(CC.translate("&&dYou killed all the Mobs"));
				sender.sendMessage(CC.translate("&dTotal&7: &5" + total));
				return true;
			} else if (args[0].equalsIgnoreCase("animals") || args[0].equalsIgnoreCase("animal")) {
				int total = 0;

				for (World world : Bukkit.getWorlds()) {
					for (Entity entity : world.getEntities()) {
						if (entity instanceof Animals) {
							entity.remove();
							total++;
						}
					}
				}

				sender.sendMessage(CC.translate("&dYou killed all the Animals."));
				sender.sendMessage(CC.translate("&dTotal&7: &5" + total));
				return true;
			} else if (args[0].equalsIgnoreCase("items") || args[0].equalsIgnoreCase("item")) {
				int total = 0;

				for (World world : Bukkit.getWorlds()) {
					for (Entity entity : world.getEntities()) {
						if (entity instanceof Item) {
							entity.remove();
							total++;
						}
					}
				}

				sender.sendMessage(CC.translate("&dYou killed all the Items."));
				sender.sendMessage(CC.translate("&dTotal&7: &5" + total));
				return true;
			}
		}
		return false;
	}
}