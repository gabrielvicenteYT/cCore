package cc.causalmc.ccore.utils;

import cc.causalmc.ccore.CCore;
import cc.causalmc.ccore.CoreAPI;
import org.bukkit.entity.Player;

public class LPUtils {
    public static String getPrefix(Player player) {
        return CC.translate(CoreAPI.getINSTANCE().getLapi().getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix());
    }
    public static String getRank(Player player) {
        return CC.translate(CoreAPI.getINSTANCE().getLapi().getGroupManager().getGroup(CoreAPI.getINSTANCE().getLapi().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup()).getDisplayName());
    }
}