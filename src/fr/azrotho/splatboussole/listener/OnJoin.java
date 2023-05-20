package fr.azrotho.splatboussole.listener;

import fr.azrotho.splatboussole.utils.ItemBuilder;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

public class OnJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Inventory inv = player.getInventory();
        inv.clear();

        event.setJoinMessage(null);

        player.setGameMode(GameMode.ADVENTURE);
        player.teleport(new Location(player.getWorld(), 20, 35, 918));
        inv.setItem(4, ItemBuilder.create(Material.COMPASS,1, "§6§lJouer!", Arrays.asList("§6Click pour Jouer!")));

    }
}
