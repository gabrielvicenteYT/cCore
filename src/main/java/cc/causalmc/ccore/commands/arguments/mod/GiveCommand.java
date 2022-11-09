package cc.causalmc.ccore.commands.arguments.mod;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class GiveCommand extends Command {

	public GiveCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("give");
		this.setDescription("give Command");
		this.setSyntax("/give <player> <item> <amount>");
		this.addRequirement(Requirement.MODERATOR);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {

				if(args.length != 3) {
					sender.sendMessage(CC.RED +"Usage: /give <player> <item> <amount>");
					return true;
				}
				if (CC.checkOffline(sender, args[0])) {

					return false;
				}

				if(args.length == 3){
					if(!(Bukkit.getPlayer(args[0]) == null)) {
						Player target = Bukkit.getPlayer(args[0]);
						if(!(Material.getMaterial(args[1].toUpperCase()) == null)){

							ItemStack Item = new ItemStack(Material.valueOf(args[1].toUpperCase()), Integer.parseInt(args[2]));
							PlayerInventory inv = target.getInventory();
							inv.addItem(Item);
							target.updateInventory();

							if (sender instanceof Player) {
								Player player = (Player) sender;
							} else {
							}
							return true;
						}
					}
				}

			 else {
			 	sender.sendMessage(CC.RED +"Usage: /give <player> <item> <amount>");
			}
		return false;
	}
}
