package cc.causalmc.ccore.menus;

import cc.causalmc.ccore.tags.Tags;
import cc.causalmc.ccore.utils.CC;
import cc.causalmc.ccore.utils.ItemBuilder;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CosmeticsMenu {

    public static List<UUID> cosMenuOpened = new ArrayList<>();

    public static void open(Player player){
        LuckPerms lapi = LuckPermsProvider.get();
        User user = lapi.getUserManager().getUser(player.getName());
        String prefixWithoutCC = user.getCachedData().getMetaData(lapi.getContextManager().getQueryOptions(user).orElse(lapi.getContextManager().getStaticQueryOptions())).getPrefix();
        String prefix = CC.translate(prefixWithoutCC);


        cosMenuOpened.add(player.getUniqueId());
        Inventory inv = Bukkit.createInventory(null, 27, CC.AQUA + "Cosmetics");
        for (int i = 0; i < 10; i++) {
            inv.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 0).name(" ").build());
        }

        inv.setItem(12,
                new ItemBuilder(Material.NAME_TAG).name(CC.translate("&bTags")).build()
        );
        inv.setItem(14,
                new ItemBuilder(Material.BEDROCK).name(CC.translate("&4SOON")).build()
        );

        for (int i = 0; i < 10; i++) {
            inv.setItem(17 + i, new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 0).name(" ").build());
        }

        player.openInventory(inv);
    }

}
