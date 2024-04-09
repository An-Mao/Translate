package anmao.mc.translate.config;

import anmao.mc.amlib.json.JsonConfig;
import com.google.gson.reflect.TypeToken;

public class Config extends JsonConfig<ConfigData> {
    public static final String file = Configs.ConfigDir + "config.json";
    public static final Config I = new Config();
    public Config() {
        super(file, """
                {
                  "onlineTranslate": false,
                  "defaultApiId": "name"
                }""", new TypeToken<>(){});
    }
    public TranslateApiData getApi(){
        return TranslateApi.INS.getApi(getDatas().getDefaultApiId());
    }
    public boolean isOnlineTranslate(){
        return getDatas().isOnlineTranslate();
    }
}
