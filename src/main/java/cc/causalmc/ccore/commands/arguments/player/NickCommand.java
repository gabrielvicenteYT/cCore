package cc.causalmc.ccore.commands.arguments.player;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager.Requirement;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.haoshoku.nick.api.NickAPI;

public class NickCommand extends Command {
    public NickCommand(JavaPlugin Core) {
        super(Core);
        this.addAlias("nick");
        this.addAlias("disguise");
        this.setDescription("nick command");
        this.setSyntax("/nick <pseudo>");
        this.addRequirement(Requirement.PLAYER);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(!player.hasPermission("sytaria.nick")) {
                player.sendMessage(CC.translate("&cVous n'avez pas la permission"));
                return false;
            }

            if (args.length == 0) {
                player.sendMessage(CC.translate("&cMauvais usage: /nick <pseudo>"));
                return false;
            } else {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if(args[0].equalsIgnoreCase(p.getName())){
                        player.sendMessage(CC.translate("&cCe joueur est déja connecté sur ce serveur."));
                        return false;
                    }
                }
                if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("reset")){
                    NickAPI.resetNick( player );
                    NickAPI.resetSkin( player );
                    NickAPI.resetUniqueId( player );
                    NickAPI.resetGameProfileName( player );
                    NickAPI.refreshPlayer( player );
                    player.sendMessage(CC.translate("&aNick Reset"));
                    return true;
                }
                String name = args[0];
                NickAPI.nick( player, name );
                NickAPI.setSkin( player, name );
                NickAPI.setUniqueId( player, name );
                NickAPI.setGameProfileName( player, name );
                NickAPI.refreshPlayer( player );
                player.sendMessage(CC.translate("&aVous êtes a présent nick sous le pseudo de: " + name));
            }
            return true;
        }
        return false;
    }
}
