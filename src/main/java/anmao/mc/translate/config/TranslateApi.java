package anmao.mc.translate.config;

import anmao.mc.amlib.json.JsonConfig;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class TranslateApi extends JsonConfig<Map<String,TranslateApiData>> {
    public static final String file = Configs.ConfigDir + "api.json";
    public static final TranslateApi INS = new TranslateApi();
    public TranslateApi() {
        super(file, """
                {
                  "name": {
                    "url":"https://????.com/",
                    "type": "POST",
                    "head": "Content-Type:application/x-www-form-urlencoded;",
                    "data": "<source>"
                  }
                }""", new TypeToken<>(){});
    }
    public TranslateApiData getApi(String s){
        return getDatas().get(s);
    }
}
