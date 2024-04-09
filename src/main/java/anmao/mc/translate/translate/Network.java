package anmao.mc.translate.translate;

import anmao.mc.translate.config.Config;
import anmao.mc.translate.config.TranslateApiData;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Network {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static String getOnlineTranslate(String s) {
        TranslateApiData api = Config.I.getApi();
        try {
            URL apiUrl = new URL(api.getUrl(s));
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod(api.getType());
            if (api.getType().equals("POST")){
                String[] heads = api.getHead().split(";");
                for (String head : heads) {
                    if (!head.isEmpty()) {
                        String[] h = head.split(":");
                        connection.setRequestProperty(h[0], h[1]);
                    }
                }
                connection.setDoOutput(true);
                String requestBody = api.getData().replace("<source>",s);
                try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                    outputStream.write(requestBody.getBytes(StandardCharsets.UTF_8));
                }
            }
            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String responseLine;
                while ((responseLine = reader.readLine()) != null) {
                    response.append(responseLine);
                }
                System.out.println("Response: " + response);
            }
            connection.disconnect();
            return response.toString();

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return s;
    }
}
