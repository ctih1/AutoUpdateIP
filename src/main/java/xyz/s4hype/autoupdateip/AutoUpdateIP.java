package xyz.s4hype.autoupdateip;

import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;


public final class AutoUpdateIP extends JavaPlugin {

    URL url;
    String lastIp;
    String newIp;
    String zoneId, dnsId, email, authKey, urlName;
    @Override
    public void onEnable() {
        System.out.println("[AutoUpdateIP] Updating IP...");
        lastIp = getConfig().getString("LastIpChanged");

        zoneId = getConfig().getString("ZoneID");
        dnsId = getConfig().getString("DnsRecordID");
        email = getConfig().getString("Email");
        authKey = getConfig().getString("AuthKey");
        urlName = getConfig().getString("URL");
        if(Objects.equals(zoneId, "null") || Objects.equals(dnsId, "null") || Objects.equals(email, "null") || Objects.equals(authKey, "null") || Objects.equals(urlName, "null")) {
            getConfig().set("ZoneID","null");
            getConfig().set("DnsRecordID","null");
            getConfig().set("Email","null");
            getConfig().set("AuthKey","null");
            getConfig().set("URL","null");
            saveConfig();
            System.out.println("[AutoUpdateIP] Default configuration created, please fill in the fields at /plugins/AutoUpdateIP/config.yml");
            return;
        }
        try {
            newIp = GetIP.GetIP();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(lastIp==null|| !lastIp.equals(newIp)) {
            try {
                if (SendRequest.Send(
                        zoneId,
                        dnsId,
                        email,
                        authKey,
                        newIp,
                        urlName
                )) {
                    System.out.print("[AutoUpdateIP] Succesfully updated IP!");
                    getConfig().set("LastIpChanged",newIp);
                    saveConfig();
                } else {
                    System.out.print("[AutoUpdateIP] Failed to update IP...");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.print("[AutoUpdateIP] Skipped updating IP, the IP is the same as before.");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
