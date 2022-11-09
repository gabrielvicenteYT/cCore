package cc.causalmc.ccore.backend;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CausalPlayer {

    private static Map<Player, CausalPlayer> causalPlayerMap = new HashMap<>();
    private Player player;
    private PlayerData playerData;

    public CausalPlayer(Player player) {
        this.player = player;
        this.playerData = new PlayerData(player.getUniqueId());
        causalPlayerMap.put(player, this);
    }

    public static CausalPlayer getPlayerInfos(Player player){
        return causalPlayerMap.get(player);
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }


    public double getCoins() {
        return playerData.getCoins();
    }
    public double getCredits() {
        return playerData.getCredits();
    }
    public String getTag() {
        return playerData.getTag();
    }
    public void resetTag() {
        playerData.resetTag();
    }
    public void setTag(String name) {
        playerData.setTag(name);
    }
    public void addCoins(double amount){
        playerData.addCoins(amount);
    }
    public void addCredits(double amount){
        playerData.addCredits(amount);
    }
    public void removeCoins(double amount){
        playerData.removeCoins(amount);
    }
    public void removeCredits(double amount){
        playerData.removeCredits(amount);
    }

    public void playerDisconnect(Player player){
        causalPlayerMap.remove(player);
    }

    public static Map<Player, CausalPlayer> getCausalPlayerMap() {
        return causalPlayerMap;
    }
}
