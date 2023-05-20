package fr.azrotho.splatboussole;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.sun.tools.javac.Main;
import fr.azrotho.splatboussole.listener.OnClick;
import fr.azrotho.splatboussole.listener.OnDamage;
import fr.azrotho.splatboussole.listener.OnInteract;
import fr.azrotho.splatboussole.listener.OnJoin;
import fr.azrotho.splatboussole.runnable.GetAllServers;
import fr.azrotho.splatboussole.runnable.PingTimerServer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SplatBoussole extends JavaPlugin {

    public static List<String> servers = new ArrayList<>();
    private static HashMap<String, String> serverMotd = new HashMap<>();
    private static HashMap<String, Integer> serverPlayers = new HashMap<>();
    private static HashMap<String, Integer> serverMaxPlayers = new HashMap<>();
    private static HashMap<String, String> serverIp = new HashMap<>();
    private static HashMap<String, Integer> serverPort = new HashMap<>();


    public static SplatBoussole instance;

        @Override
        public void onEnable() {
            getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
            getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeCordListener());
            instance = this;

            Bukkit.getServer().getPluginManager().registerEvents(new OnDamage(), this);
            Bukkit.getServer().getPluginManager().registerEvents(new OnJoin(), this);
            Bukkit.getServer().getPluginManager().registerEvents(new OnInteract(), this);
            Bukkit.getServer().getPluginManager().registerEvents(new OnClick(), this);

            GetAllServers getAllServers = new GetAllServers();
            getAllServers.runTaskTimer(this, 0, 20);

            PingTimerServer pingTimerServer = new PingTimerServer();
            pingTimerServer.runTaskTimer(this, 0, 60);


        }

    public List<String> getServers() {
        return servers;
    }

    public static HashMap<String, String> getServerMotd() {
        return serverMotd;
    }

    public static HashMap<String, Integer> getServerPlayers() {
        return serverPlayers;
    }

    public static HashMap<String, Integer> getServerMaxPlayers() {
        return serverMaxPlayers;
    }

    public static HashMap<String, String> getServerIp() {
        return serverIp;
    }

    public static HashMap<String, Integer> getServerPort() {
        return serverPort;
    }

    @Override
        public void onDisable() {
        }

    public static SplatBoussole getInstance() {
        return instance;
    }

    private class BungeeCordListener implements PluginMessageListener {

        @Override
        public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
            if(!s.equals("BungeeCord")) return;
            ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
            String subchannel = in.readUTF();
            if(subchannel.equals("GetServers")) {
                servers.clear();
                String[] servers = in.readUTF().split(", ");
                SplatBoussole.servers.addAll(Arrays.asList(servers));
            }

            if(subchannel.equals("ServerIP")) {
                String server = in.readUTF();
                String ip = in.readUTF();
                int port = in.readUnsignedShort();
                serverIp.put(server, ip);
                serverPort.put(server, port);
            }
        }
    }
}
