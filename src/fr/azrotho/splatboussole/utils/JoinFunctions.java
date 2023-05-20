package fr.azrotho.splatboussole.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.azrotho.splatboussole.SplatBoussole;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class JoinFunctions {
    public static void connect(String type, Player player) {
        List<String> servers = SearchInServers.search(type);
        Collections.sort(servers);
        String server = servers.get(0);
        boolean serverFound = false;
        while(!serverFound) {
            if (SplatBoussole.getServerMotd().get(server).contains("READY") || SplatBoussole.getServerMotd().get(server).contains("STARTING")) {
                serverFound = true;
            } else {
                servers.remove(server);
                server = servers.get(0);
            }
        }
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(SplatBoussole.getInstance(), "BungeeCord", out.toByteArray());
    }
}
