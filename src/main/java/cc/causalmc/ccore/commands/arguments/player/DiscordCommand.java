package cc.causalmc.ccore.commands.arguments.player;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordCommand extends Command {
    public DiscordCommand(JavaPlugin Core) {
        super(Core);
        this.addAlias("discord");
        this.addAlias("ds");
        this.setDescription("Discord");
        this.setSyntax("/discord");
        this.addRequirement(Requirement.PLAYER);

    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {

        if (!(sender instanceof Player)) return false;

        Player p = (Player) sender;

        p.sendMessage(CC.translate("&dOur discord: &5https://discord.causal.cc"));

        return true;
    }
}
