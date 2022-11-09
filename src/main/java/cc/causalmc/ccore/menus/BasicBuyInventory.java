package cc.causalmc.ccore.menus;

import cc.causalmc.ccore.CCore;
import cc.causalmc.ccore.utils.CC;
import cc.causalmc.ccore.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class BasicBuyInventory{




    public static void Buy(UUID uuid, float coins, float credits, String permission){
        Player player = Bukkit.getPlayer(uuid);
        ItemStack book = new ItemBuilder(Material.BOOK)
            .name(CC.translate("&dDo you really wanna &5Buy &dthis?"))
            .lore(CC.MENU_BAR,
                    CC.translate("&6Price:"),
                    CC.translate("&6Coins: &e" + coins),
                    CC.translate("&6Credits: &b" + credits),
                    CC.MENU_BAR)
        .build();


        Inventory toReturn = Bukkit.createInventory(null, 27, CC.AQUA + "Buy confirmation");
        toReturn.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 6).name(" ").build());
        toReturn.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 6).name(" ").build());
        toReturn.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 6).name(" ").build());
        toReturn.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 6).name(" ").build());
        toReturn.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 6).name(" ").build());
        toReturn.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 6).name(" ").build());
        toReturn.setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 6).name(" ").build());
        toReturn.setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 6).name(" ").build());
        toReturn.setItem(25, new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 6).name(" ").build());
        toReturn.setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE).durability((short) 6).name(" ").build());
        toReturn.setItem(11, new ItemBuilder(Material.STAINED_CLAY).durability((short) 5).name(CC.translate("&aYes")).build());
        toReturn.setItem(13, book);
        toReturn.setItem(15, new ItemBuilder(Material.STAINED_CLAY).durability((short) 14).name(CC.translate("&cNo")).build());
        player.openInventory(toReturn);
        CCore.buyInv.put(uuid, permission);
    }



}
