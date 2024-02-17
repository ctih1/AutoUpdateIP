package xyz.s4hype.autoupdateip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetIP {
    public static String GetIP() throws IOException {
        URL obj = new URL("https://ipinfo.io/ip");
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();
        StringBuilder content = new StringBuilder();
        if(responseCode==HttpURLConnection.HTTP_OK) {
            String inputLine;
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            httpURLConnection.disconnect();
            return content.toString();
        }
        else {
            return "1.2.3.4";
        }
    }
}
