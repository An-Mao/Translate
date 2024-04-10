package anmao.mc.translate.translate;

import anmao.mc.translate.JavaScriptSupport;
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
    public static String getOnlineTranslate(String source) {
        TranslateApiData api = Config.I.getApi();
        //System.out.println("api ::"+api);
        String requestBody;
        if (api.getData().endsWith(".js")) {
            requestBody = JavaScriptSupport.jsData(source);
        } else {
            requestBody = api.getData().replace("<source>", source);
        }
        //System.out.println("data ::"+requestBody);

        String d = sendData(api.getUrl(source),api.getType(),api.getHead(),requestBody);
        //System.out.println("net ::"+d);

        if (api.getData().endsWith(".js")) {
            requestBody = JavaScriptSupport.jsResult(source,d);
        } else {
            requestBody = api.getData().replace("<translate>", d);
        }
        //System.out.println("result ::"+requestBody);
        return requestBody;

    }
    public static String sendData(String url, String type, String dataHead, String dataSend){
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod(type);
            if (type.equals("POST")){
                String[] heads = dataHead.split(";");
                for (String head : heads) {
                    if (!head.isEmpty()) {
                        String[] h = head.split(":");
                        connection.setRequestProperty(h[0], h[1]);
                    }
                }
                connection.setDoOutput(true);
                try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                    outputStream.write(dataSend.getBytes(StandardCharsets.UTF_8));
                }
            }
            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String responseLine;
                while ((responseLine = reader.readLine()) != null) {
                    response.append(responseLine);
                }
                //System.out.println("Response: " + response);
            }
            connection.disconnect();
            return response.toString();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return "";
    }
}
