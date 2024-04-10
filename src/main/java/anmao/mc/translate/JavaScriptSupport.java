package anmao.mc.translate;

import anmao.mc.amlib.javascript.EasyJS;
import anmao.mc.translate.config.Config;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class JavaScriptSupport {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static String jsData(String source){
        Object result = EasyJS.creat()
                .addParameter("source",source)
                .runFile(Config.I.getJsFileCon());
        if (result != null){
            return result.toString();
        }
        return source;
    }
    public static String jsResult(String source,String translate){
        Object result = EasyJS.creat()
                .addParameter("source",source)
                .addParameter("translate",translate)
                .runFile(Config.I.getResult());
        if (result != null){
            return result.toString();
        }
        return translate;
    }
}
