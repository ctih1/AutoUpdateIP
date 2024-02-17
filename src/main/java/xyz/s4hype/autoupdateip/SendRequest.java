 package xyz.s4hype.autoupdateip;

import java.io.IOException;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SendRequest {
    static CloseableHttpClient httpClient;
    static HttpPatch httpPatch;
    static String url;
    static StringEntity body;
    public static boolean Send(
            String zoneId,
            String dnsRecordId,
            String email,
            String authKey,
            String IP,
            String urlName
    ) throws IOException {
        body = new StringEntity("{"
            + String.format("\"content\": \"%s\",",IP)
            + String.format("\"name\": \"%s\",",urlName)
            + "\"proxiable\": false,"
            + "\"proxied\": false,"
            + "\"type\": \"A\","
            + "\"comment\": \"Automatically changed - AutoUpdateIP\","
            + "\"ttl\": 1"
        + "}");
        body.setContentType("application/json");
        url = String.format("https://api.cloudflare.com/client/v4/zones/%s/dns_records/%s",zoneId,dnsRecordId);
        httpClient = HttpClients.createDefault();
        httpPatch = new HttpPatch(url);
        httpPatch.setHeader("Authorization", "Bearer "+authKey);
        httpPatch.setHeader("X-Auth-Email",email);
        httpPatch.setEntity(body);
        String response = EntityUtils.toString(httpClient.execute(httpPatch).getEntity(), "UTF-8");
        return response.contains("\"success\":true");
    }
}
