package cc.causalmc.ccore.listeners;

import cc.causalmc.ccore.CCore;
import cc.causalmc.ccore.backend.CausalPlayer;
import cc.causalmc.ccore.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BuyInventoryListener implements Listener {

    @EventHandler
    public void onBuyClick(InventoryClickEvent e) {

        if(e.getInventory().getName().equalsIgnoreCase(CC.AQUA + "Buy confirmation")){
            e.setCancelled(true);
            if(e.getCurrentItem().hasItemMeta()
                 && e.getCurrentItem() != null
                 && e.getCurrentItem().getItemMeta().hasDisplayName()
                 && !Objects.equals(e.getCurrentItem().getItemMeta().getDisplayName(), " ")) {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&aYes"))){
                    e.getWhoClicked().closeInventory();
                    ItemStack item = e.getInventory().getItem(13);
                    if(CCore.buyInv.containsKey(e.getWhoClicked().getUniqueId())){
                        Player player = (Player) e.getWhoClicked();
                        List<String> listC = Arrays.asList(item.getItemMeta().getLore().get(2).split(" "));
                        int coins = Integer.parseInt(listC.get(1).substring(2));
                        List<String> listR = Arrays.asList(item.getItemMeta().getLore().get(3).split(" "));
                        int credits = Integer.parseInt(listR.get(1).substring(2));
                        String perm = CCore.buyInv.get(player.getUniqueId());
                        CCore.buyInv.remove(player.getUniqueId());

                        CausalPlayer causalPlayer = CausalPlayer.getPlayerInfos(player);
                        if(causalPlayer.getCoins() >= coins && causalPlayer.getCredits() >= credits){
                            causalPlayer.removeCoins(coins);
                            causalPlayer.removeCredits(credits);
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getUniqueId().toString() + " permission set " + perm);
                            player.sendMessage(CC.translate("&dYou have &5purchased this"));
                        } else {
                            player.sendMessage(CC.translate("&dYou &5don't have &denough money to buy this"));
                        }
                    }
                } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate("&cNo"))){
                    CCore.buyInv.remove(e.getWhoClicked().getUniqueId());
                    e.getWhoClicked().closeInventory();
                }
            }
        }
    }
}
