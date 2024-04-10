package anmao.mc.translate.config;

import anmao.mc.amlib.json.JsonConfig;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class TranslateApi extends JsonConfig<Map<String,TranslateApiData>> {
    public static final String file = Configs.ConfigDir + "api.json";
    public TranslateApi() {
        super(file, """
                {
                  "baidu": {
                    "url": "https://fanyi-api.baidu.com/api/trans/vip/translate",
                    "type": "POST",
                    "head": "Content-Type:application/x-www-form-urlencoded;",
                    "data": "baidu.js",
                    "result": "baiduR.js"
                  }
                }""", new TypeToken<>(){});
    }
    public TranslateApiData getApi(String s){
        return getDatas().get(s);
    }
}
