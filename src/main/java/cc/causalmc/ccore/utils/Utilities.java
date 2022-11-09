package cc.causalmc.ccore.utils;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Utilities {
    public static List<Player> getOnlinePlayers() {
            List<Player> players = new ArrayList<>();
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                players.add(player);
            }
            return players;
    }

    public static void playSound(Player player, Sound sound) {
         player.playSound(player.getLocation(), sound, 2.0F, 2.0F);
    }
    public static void playSound(Player player, String sound) {
         try {
           player.playSound(player.getLocation(), Sound.valueOf(sound), 2.0F, 2.0F);
         } catch (Exception exception) {}

 }
    public static int getPing(Player player) {
        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
        return entityPlayer.ping;
    }
}