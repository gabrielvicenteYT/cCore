package cc.causalmc.ccore.listeners;

import cc.causalmc.ccore.CoreAPI;
import cc.causalmc.ccore.backend.CausalPlayer;
import cc.causalmc.ccore.tags.Tags;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static org.bukkit.Bukkit.getServer;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        if(getServer().getPluginManager().isPluginEnabled("CTF")) return;
        if(getServer().getPluginManager().isPluginEnabled("sUhc")) return;
        Player player = event.getPlayer();
        String prefix = CoreAPI.getINSTANCE().getPrefix(player);
        String tagName = CausalPlayer.getPlayerInfos(player).getTag();
        String tag = CC.translate(Tags.getTagPrefixByName(tagName));

        event.setFormat(tag + prefix + "%1$s §7» " + (player.hasPermission("causal.rankedchat") ? "§f" : "§7") + "%2$s");
    }
}
