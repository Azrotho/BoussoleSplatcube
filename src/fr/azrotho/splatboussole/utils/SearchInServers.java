package fr.azrotho.splatboussole.utils;

import fr.azrotho.splatboussole.SplatBoussole;

import java.util.List;

public class SearchInServers {
    public static List<String> search(String search) {
        List<String> serversDone = new java.util.ArrayList<>();
        for(String server : SplatBoussole.servers) {
            if(server.contains(search) && !server.contains("private")) {
                if (SplatBoussole.getServerMotd().get(server).contains("READY") || SplatBoussole.getServerMotd().get(server).contains("STARTING")) {
                    serversDone.add(server);
                }
            }
        }
        return serversDone;
    }
}
