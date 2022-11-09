package cc.causalmc.ccore;

import cc.causalmc.ccore.backend.CausalPlayer;
import cc.causalmc.ccore.menus.BasicBuyInventory;
import cc.causalmc.ccore.tags.Tags;
import cc.causalmc.ccore.utils.CC;
import cc.causalmc.ccore.utils.StringUtils;
import io.github.zowpy.jedisapi.JedisAPI;
import lombok.Getter;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CoreAPI {


    @Getter
    public LuckPerms lapi = LuckPermsProvider.get();
    @Getter
    public static CoreAPI INSTANCE;
    public CoreAPI(){
        INSTANCE = this;
    }


    public CausalPlayer getCausalPlayer(Player player) {
        return new CausalPlayer(player);
    }
    public JedisAPI getJedis() {
        return CCore.getInstance().getApi().getJedis();
    }

    public void buy(UUID uuid, int coins, int credits, String permission){
        BasicBuyInventory.Buy(uuid, coins, credits, permission);
    }
    public String getTag(Player player) {
        String tn = CausalPlayer.getPlayerInfos(player).getTag();
        String t = Tags.getTagPrefixByName(tn);
        return CC.translate(t);
    }

    public String getPrefix(Player player) {
        String prefix = lapi.getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getPrefix();
        return CC.translate(prefix);
    }

    public String getRankDisplayName(Player player) {
        String rank = lapi.getGroupManager().getGroup(lapi.getUserManager().getUser(player.getUniqueId()).getPrimaryGroup()).getDisplayName();
        return CC.translate(rank);
    }

    public void setMaxPlayers(int slots) {
        StringUtils.setSlots(slots);
    }

    public String getRank(Player player){
        User user = lapi.getUserManager().getUser(player.getName());
        assert user != null;
        return user.getPrimaryGroup();
    }

    public Group getGroupByName(String name) {
        // group doesn't exist.
        return lapi.getGroupManager().getGroup(name);
    }

  /*  public ArrayList<Group> getGroups() {
        ArrayList<Group> Groups = new ArrayList<>();
        HashMap<Group, Integer> hm= new HashMap<>();
        lapi.getGroupManager().loadAllGroups();
        lapi.getGroupManager().getLoadedGroups().stream().sorted();
        for (Group group : lapi.getGroupManager().getLoadedGroups()) {
            int weight = group.getWeight().getAsInt();
            hm.put(group, weight);
        }
        TreeMap<Group, Integer> tm =new TreeMap<>(hm);
        tm.descendingMap();
        Iterator itr=tm.keySet().iterator();
        while(itr.hasNext()){
            Group key=(Group)itr.next();
            Groups.add(key);
        }
        return Groups;
    } */

    public String getTPS() {
        StringBuilder sb = new StringBuilder(" ");
        for ( double tps : MinecraftServer.getServer().recentTps )
        {
            sb.append(format(tps));
            sb.append( ", " );
        }
        return sb.toString();
    }
    public String getLatestTPS() {
        StringBuilder sb = new StringBuilder(" ");
        for ( double tps : MinecraftServer.getServer().recentTps )
        {
            sb.append(format(tps));
            sb.append( ", " );
        }
        return sb.substring( 0, sb.length() - 2 );
    }

    private String format(double tps)
    {
        return ( ( tps > 18.0 ) ? CC.GREEN : ( tps > 16.0 ) ? CC.YELLOW : CC.RED ).toString()
                + ( ( tps > 20.0 ) ? "*" : "" ) + Math.min( Math.round( tps * 100.0 ) / 100.0, 20.0 );
    }
}
