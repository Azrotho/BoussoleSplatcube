package fr.azrotho.splatboussole.listener;

import fr.azrotho.splatboussole.utils.CreateInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnInteract implements Listener {
    @EventHandler
    public static void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (event.getItem().getItemMeta() == null) return;
        if (event.getItem().getItemMeta().getDisplayName().equals("§6§lJouer!")) {
            CreateInventory.createMainInventory(event.getPlayer());
        }
    }
}
