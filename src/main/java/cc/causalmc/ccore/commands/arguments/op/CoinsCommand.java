package cc.causalmc.ccore.commands.arguments.op;

import cc.causalmc.ccore.CCore;
import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.backend.CausalPlayer;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CoinsCommand extends Command implements TabCompleter {

	public CoinsCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("coins");
		this.setDescription("coins Command");
		this.setSyntax("/coins");
		this.addRequirement(Requirement.OP);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(args.length < 2 || args.length > 3) {
				p.sendMessage(CC.translate("&c/coins <add/remove> <player> <amount>"));
				p.sendMessage(CC.translate("&c/coins <get> <player> "));
				return false;
			}
			if (CC.checkOffline(sender, args[1])){
				return false;
			} else {
				Player target = Bukkit.getPlayer(args[1]);
				CausalPlayer targetInfo = new CausalPlayer(target);

				if(args.length == 3) {
					double amount = Double.parseDouble(args[2]);
					switch (args[0]) {
						case "add":
							targetInfo.addCoins(amount);
							Bukkit.getScheduler().runTaskLaterAsynchronously(CCore.getInstance(), () -> p.sendMessage(CC.translate("&dYou &5added &5" + amount +  "&dcoins to this player, bringing his total to &5" + targetInfo.getCoins() + "&dcoins.")), 10L);

							break;
						case "remove":
							targetInfo.removeCoins(amount);
									Bukkit.getScheduler().runTaskLaterAsynchronously(CCore.getInstance(), () -> p.sendMessage(CC.translate("&dYou &5removed &5" + amount +  "&dcoins to this player, bringing his total to &5" + targetInfo.getCoins() + "&dcoins.")), 10L);
							break;
						default:
							p.sendMessage(CC.translate("&c/coins <add/remove> <player> <amount>"));
							p.sendMessage(CC.translate("&c/coins <get> <player> "));
							return false;
					}
				}
				if(args.length == 2) {
					p.sendMessage(CC.translate("&dThis player has &5" + targetInfo.getCoins() + "&dcoins."));
				}
			}
			return true;
		}
		return false;
	}
	@Override
	public List<String> onTabComplete (CommandSender commandSender, org.bukkit.command.Command command, String s, String[]args){
		List<String> list = new ArrayList<>();
		switch(args.length){
			case 1:
				list.clear();
				list.add("get");
				list.add("add");
				list.add("remove");
				return list;
			case 3:
				list.clear();
				list.add("<amount>");
				return list;
			default:
				return null; // retourne les joueurs co
		}
	}
}
