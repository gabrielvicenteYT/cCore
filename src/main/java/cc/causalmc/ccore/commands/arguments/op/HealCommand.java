package cc.causalmc.ccore.commands.arguments.op;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

public class HealCommand extends Command {

	public HealCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("heal");
		this.setDescription("Heal Command");
		this.setSyntax("/heal <player|all>");
		this.addRequirement(Requirement.OP);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if(sender instanceof Player) {
			Player player = (Player) sender;

			if(args.length == 0) {
				this.healPlayer(player);

			} else {
				if(args[0].equalsIgnoreCase("all")) {
					for(Player online : Bukkit.getOnlinePlayers()) {
						this.healPlayer(online);
						online.sendMessage(CC.translate("&dYou have been &5healed &dby &5" + player.getName() + "&d."));
					}

				} else {
					Player target = Bukkit.getPlayer(args[0]);

					if (CC.checkOffline(sender, args[0])) {

						return false;
					}

					this.healPlayer(target);

					target.sendMessage(CC.translate("&dYou have been &5healed &dby &5" + player.getName() + "&d."));

				}
			}

			return true;
		}


		if(args.length == 0) {
			sender.sendMessage(CC.translate("&cUsage: /heal <player|all>"));
		} else {
			if(args[0].equalsIgnoreCase("all")) {
				for(Player online : Bukkit.getOnlinePlayers()) {
					this.healPlayer(online);
					online.sendMessage(CC.translate("&You have been &5healed &dby " + CC.CONSOLE + "&d."));
				}

			} else {
				Player target = Bukkit.getPlayer(args[0]);

				if (CC.checkOffline(sender, args[0])) {

					return false;
				}

				this.healPlayer(target);

				target.sendMessage(CC.translate("&You have been &5healed &dby " + CC.CONSOLE + "&d."));
				sender.sendMessage(CC.translate("&dYou have &5healed " + target.getDisplayName() + "&d."));
			}
		}
		return false;
	}


	public void healPlayer(Player player) {
		player.setHealth(player.getMaxHealth());
		player.setFoodLevel(20);
		player.setSaturation(10);
		player.setFireTicks(0);

		for(PotionEffect effects : player.getActivePotionEffects()) {
			player.removePotionEffect(effects.getType());
		}
	}
}