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

public class TagMenu {

    public static List<UUID> tagMenuOpened = new ArrayList<>();
    public static List<UUID> tagConfirmMenuOpened = new ArrayList<>();

    public static void open(Player player){
        LuckPerms lapi = LuckPermsProvider.get();
        User user = lapi.getUserManager().getUser(player.getName());
        String prefixWithoutCC = user.getCachedData().getMetaData(lapi.getContextManager().getQueryOptions(user).orElse(lapi.getContextManager().getStaticQueryOptions())).getPrefix();
        String prefix = CC.translate(prefixWithoutCC);


        tagMenuOpened.add(player.getUniqueId());
        Inventory inv = Bukkit.createInventory(null, 45, CC.AQUA + "Tags");
        for (int i = 0; i < 9; i++) {
            inv.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 14).name(" ").build());
        }
        for (Tags tag : Tags.values()) {
            ItemStack tagItem = new ItemBuilder(Material.NAME_TAG)
                    .name(CC.translate("&5" + tag.getName()))
                    .lore(
                            CC.MENU_BAR,
                            CC.translate("&6Tag: " + tag.getPrefix()),
                            player.hasPermission(tag.getPermission()) ? CC.translate("&6Price: &aOwned") : CC.translate("&6Prix: &e" + tag.getPrice() + " coins"),
                            CC.translate("&6Example: " + tag.getPrefix() + prefix + player.getName() + " &7» " + (player.hasPermission("causal.rankedchat") ? "&f" : "&7") + "Hey!"),
                            CC.MENU_BAR
                    )
                    .build();
            inv.setItem(tag.getTagId() + 8, tagItem);
        }
        for (int i = 0; i < 9; i++) {
            inv.setItem(36 + i, new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 14).name(" ").build());
        }
        inv.setItem(40, new ItemBuilder(Material.BARRIER).name(CC.translate("&cReset")).build());

        player.openInventory(inv);
    }

    public static void openConfirm(Player player) {

    }
}
