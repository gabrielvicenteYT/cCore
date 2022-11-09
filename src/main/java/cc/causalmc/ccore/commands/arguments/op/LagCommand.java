package cc.causalmc.ccore.commands.arguments.op;


import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.management.ManagementFactory;
import java.util.concurrent.CompletableFuture;

public class LagCommand extends Command {

	public LagCommand(JavaPlugin Core) {
		super(Core);
		this.addAlias("lag");
		this.addAlias("serverlag");
		this.setDescription("lag command");
		this.setSyntax("/lag");
		this.addRequirement(Requirement.OP);
	}


	@Override
	public boolean execute(CommandSender sender, String[] args) throws Exception {
		StringBuilder sb = new StringBuilder(" ");
		for ( double tps : MinecraftServer.getServer().recentTps )
		{
			sb.append(format(tps));
			sb.append( ", " );
		}

		long serverTime = ManagementFactory.getRuntimeMXBean().getStartTime();
		String uptime = DurationFormatUtils.formatDurationWords(System.currentTimeMillis() - serverTime, true, true);

		String tps = sb.substring( 0, sb.length() - 2 );
		CompletableFuture.runAsync(() -> {
			sender.sendMessage(CC.CHAT_BAR);
			sender.sendMessage(CC.translate("&5&lServer Info&7:"));
			sender.sendMessage(CC.translate(" &7* &dTPS&7: &5" + tps));
			sender.sendMessage(CC.translate(" &7* &dUptime&7: &5" + uptime));
			sender.sendMessage(CC.translate(" &7* &dMax Memory&7: &5" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + " &dMB"));
			sender.sendMessage(CC.translate(" &7* &dAllocated Memory&7: &5" + Runtime.getRuntime().totalMemory() / 1024 / 1024 + " &dMB"));
			sender.sendMessage(CC.translate(" &7* &dFree Memory&7: &5" + Runtime.getRuntime().freeMemory() / 1024 / 1024 + " &dMB"));
			sender.sendMessage("");
			sender.sendMessage(CC.translate("&5&lWorlds&7:"));

			for(World world : Bukkit.getWorlds()) {
				sender.sendMessage(CC.translate(" &7* &5" + world.getName() + "&7: &dChunks Loaded&7: &5" + world.getLoadedChunks().length + "&7, &dEntities&7: &5" + world.getEntities().size()));
			}

			sender.sendMessage(CC.CHAT_BAR);

		});
		return true;

	}
	private String format(double tps)
	{
		return ( ( tps > 18.0 ) ? CC.GREEN : ( tps > 16.0 ) ? CC.YELLOW : CC.RED ).toString()
				+ ( ( tps > 20.0 ) ? "*" : "" ) + Math.min( Math.round( tps * 100.0 ) / 100.0, 20.0 );
	}
}