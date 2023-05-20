package fr.azrotho.splatboussole.utils;

import fr.azrotho.splatboussole.SplatBoussole;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.List;

public class CreateInventory {
    public static void createMainInventory(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, "§6§lSplatBoussole");
        inv.setItem(4, ItemBuilder.create(Material.ENDER_EYE, 1, "§6§lRejoindre une partie", Arrays.asList("§7Clique gauche pour rejoindre une partie","§7Clique droit pour choisir votre serveur")));
        player.openInventory(inv);
    }

    public static Inventory openInventoryServerList(String game){
        Inventory inv = Bukkit.createInventory(null, 27, "§6Liste des serveurs " + game);

        List<String> servers = SearchInServers.search(game);
        for(String server : servers){
            if(SplatBoussole.getServerMotd().get(server).contains("READY")) {
                inv.addItem(ItemBuilder.create(Material.EMERALD_BLOCK, 1, "§a" + server, Arrays.asList("§6Click pour rejoindre le serveur!", "§7Joueurs: §6" + SplatBoussole.getServerPlayers().get(server) + "§7/§6" + SplatBoussole.getServerMaxPlayers().get(server))));
            }
            if(SplatBoussole.getServerMotd().get(server).contains("STARTING")) {
                inv.addItem(ItemBuilder.create(Material.GOLD_BLOCK, 1, "§6" + server, Arrays.asList("§6Click pour rejoindre le serveur, la partie va bientôt démarrer!", "§7Joueurs: §6" + SplatBoussole.getServerPlayers().get(server) + "§7/§6" + SplatBoussole.getServerMaxPlayers().get(server))));
            }
            if(SplatBoussole.getServerMotd().get(server).contains("INGAME")) {
                inv.addItem(ItemBuilder.create(Material.BARRIER, 1, "§c" + server, Arrays.asList("§6La partie est en cours!", "§7Joueurs: §6" + SplatBoussole.getServerPlayers().get(server) + "§7/§6" + SplatBoussole.getServerMaxPlayers().get(server))));
            }
            if(SplatBoussole.getServerMotd().get(server).contains("PREPARING")) {
                inv.addItem(ItemBuilder.create(Material.BARRIER, 1, "§c" + server, Arrays.asList("§6Le serveur est entrain de démarrer", "§7Joueurs: §6" + SplatBoussole.getServerPlayers().get(server) + "§7/§6" + SplatBoussole.getServerMaxPlayers().get(server))));
            }
            if(SplatBoussole.getServerPlayers().get(server).equals(SplatBoussole.getServerMaxPlayers().get(server))){
                inv.addItem(ItemBuilder.create(Material.BARRIER, 1, "§c" + server, Arrays.asList("§6Le serveur est complet!", "§7Joueurs: §6" + SplatBoussole.getServerPlayers().get(server) + "§7/§6" + SplatBoussole.getServerMaxPlayers().get(server))));
            }
        }
        return inv;
    }
}
