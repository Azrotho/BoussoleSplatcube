package fr.azrotho.splatboussole.runnable;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.azrotho.splatboussole.SplatBoussole;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class GetAllServers extends BukkitRunnable {
    @Override
    public void run() {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("GetServers");
            Bukkit.getServer().sendPluginMessage(SplatBoussole.getInstance(), "BungeeCord", out.toByteArray());
            for(String server : SplatBoussole.servers){
                ByteArrayDataOutput out2 = ByteStreams.newDataOutput();
                out2.writeUTF("ServerIP");
                out2.writeUTF(server);
                Bukkit.getServer().sendPluginMessage(SplatBoussole.getInstance(), "BungeeCord", out2.toByteArray());
        }
    }
}
