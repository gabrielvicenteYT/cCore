package cc.causalmc.ccore.commands.arguments.player;

import cc.causalmc.ccore.commands.manager.Command;
import cc.causalmc.ccore.commands.manager.CommandManager;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class StoreCommand extends Command {
    public StoreCommand(JavaPlugin Core) {
        super(Core);
        this.addAlias("donate");
        this.addAlias("store");
        this.addAlias("boutique");
        this.setDescription("Store command");
        this.setSyntax("/store");
        this.addRequirement(CommandManager.Requirement.PLAYER);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {

            if(args.length == 0) {
                sender.sendMessage(CC.translate("&dYou can donate to us @ &5store.causal.cc"));
            return true;
            }

        return false;
    }
}
