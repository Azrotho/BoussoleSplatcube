package fr.azrotho.splatboussole.runnable;

import fr.azrotho.splatboussole.SplatBoussole;
import fr.azrotho.splatboussole.utils.ServerPinger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.CompletableFuture;

public class PingTimerServer extends BukkitRunnable {
    @Override
    public void run() {

        for(Player player : Bukkit.getOnlinePlayers()) {
            player.setFoodLevel(20);
            player.setSaturation(20);
            player.setHealth(20);
        }

        for (String server : SplatBoussole.getServerPort().keySet()) {
            CompletableFuture<ServerPinger.PingResult> serverPinger = ServerPinger.ping(SplatBoussole.getServerIp().get(server), SplatBoussole.getServerPort().get(server));
            serverPinger.thenAccept(result -> {
                SplatBoussole.getServerPlayers().put(server, result.getPlayers());
                SplatBoussole.getServerMaxPlayers().put(server, result.getMaxPlayers());
                SplatBoussole.getServerMotd().put(server, result.getMotd());
            });

        }
    }
}
