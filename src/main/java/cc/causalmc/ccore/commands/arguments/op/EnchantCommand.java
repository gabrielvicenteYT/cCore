package cc.causalmc.ccore.commands.arguments.op;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import cc.causalmc.ccore.utils.NumberUtils;
import cc.causalmc.ccore.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class EnchantCommand extends Command {

	public EnchantCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("enchant");
		this.setDescription("enchant Command");
		this.setSyntax("/enchant <enchantment> <level>");
		this.addRequirement(Requirement.OP);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if (sender instanceof Player){
			Player player = (Player) sender;

			if(args.length < 2) {
				player.sendMessage(CC.translate("&cUsage: /enchant <enchantment> <level>"));
			} else {
				ItemStack item = player.getItemInHand();

				if(item == null || item.getType() == Material.AIR) {
					player.sendMessage(CC.translate("&cYou need to have the item you want to enchant in your hand."));
					return false;
				}

				if(!NumberUtils.isInteger(args[1])) {
					player.sendMessage(CC.translate("&cThe level must be a number."));
					return false;
				}

				int level = Integer.parseInt(args[1]);

				if(level < 0) {
					player.sendMessage(CC.translate("&cThe level must be higher than 0."));
					return false;
				}

				if (level > 10 && !player.isOp()) {
					player.sendMessage(CC.translate("&cThe level must be lower than 10."));
					return false;
				}


				String enchantment = StringUtils.getEnchantment(args[0]);

				if(level == 0) {
					if(item.containsEnchantment(Enchantment.getByName(enchantment))) {
						item.removeEnchantment(Enchantment.getByName(enchantment));

						player.sendMessage(CC.translate("&dYou removed the enchantement &5" + args[0].toUpperCase() + "&d."));
						return true;
					} else {
						player.sendMessage(CC.translate("&dThis item does not have the enchantement &5" + args[0].toUpperCase() + "&d."));
					}
				} else {
					item.addUnsafeEnchantment(Enchantment.getByName(enchantment), level);

					player.sendMessage(CC.translate("&dYou enchanted the item &5" + item.getType() + "&d with &5" + args[0].toUpperCase() + "&d."));
					return true;
				}
			}
		}
		return false;
	}
}