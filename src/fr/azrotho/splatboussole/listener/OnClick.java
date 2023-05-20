package fr.azrotho.splatboussole.listener;

import fr.azrotho.splatboussole.utils.CreateInventory;
import fr.azrotho.splatboussole.utils.JoinFunctions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class OnClick implements Listener {
    @EventHandler
    public static void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
        Inventory inv = event.getInventory();
        InventoryView view = event.getView();
        if (inv == null) return;
        if (view == null) return;
        if (view.getTitle() == null) return;
        if (view.getTitle().equals("§6§lSplatBoussole")) {
            if (event.getCurrentItem() == null) return;
            if (event.getCurrentItem().getItemMeta() == null) return;
            InventoryAction action = event.getAction();
            if (action == null) return;
            if (action.equals(InventoryAction.PICKUP_ALL)) {
                JoinFunctions.connect("splatcube", (Player) event.getWhoClicked());
            }
            if (action.equals(InventoryAction.PICKUP_HALF)) {
                ((Player) event.getWhoClicked()).openInventory(CreateInventory.openInventoryServerList("splatcube"));
            }
        }
    }
}
