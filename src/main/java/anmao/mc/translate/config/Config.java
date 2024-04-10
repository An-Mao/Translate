package anmao.mc.translate.config;

import anmao.mc.amlib.json.JsonConfig;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class Config extends JsonConfig<ConfigData> {
    public static final TranslateApi Api = new TranslateApi();
    public static final String file = Configs.ConfigDir + "config.json";
    public static final Config I = new Config();
    private Reader jsf = null;
    private Reader ResultFile = null;


    public Config() {
        super(file, """
                {
                  "onlineTranslate": false,
                  "defaultApiId": "name",
                  "RequestInterval": 3000
                }""", new TypeToken<>(){});
    }
    public TranslateApiData getApi(){
        return Api.getApi(getDatas().getDefaultApiId());
    }
    public boolean isOnlineTranslate(){
        return getDatas().isOnlineTranslate();
    }
    public String getJsFile(String file){
        return Configs.ConfigDir_JS + file;
    }
    public Reader getJsFileCon(){
        /*
        if (jsf == null) {
            try {
                jsf = new FileReader(getJsFile(getApi().getData()));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        return jsf;

         */
        try {
            return new FileReader(getJsFile(getApi().getData()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Reader getResult(){
        try {
            return new FileReader(getJsFile(getApi().getResult()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
