package anmao.mc.translate.translate;

import anmao.mc.amlib.bytes.Byte;
import anmao.mc.translate.config.Config;
import anmao.mc.translate.config.Configs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mojang.logging.LogUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DB {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final ConcurrentHashMap<String,data> TranslateMap = new ConcurrentHashMap<>();
    private static Thread tt = null;
    private static String getTranslate(String source) {
        if (Config.I.isOnlineTranslate()) {
            return Network.getOnlineTranslate(source);
        }
        return source;
    }

    public static String get(String pack,String source) {
        String filePath = Configs.ConfigDir_Lang + pack +".json";
        String value = source;
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            File file = new File(filePath);
            if (file.exists()) {
                JsonObject jsonObject;
                try (FileReader fileReader = new FileReader(filePath)) {
                    jsonObject = gson.fromJson(fileReader, JsonObject.class);
                }
                if (jsonObject.has(source)) {
                    value = jsonObject.get(source).getAsString();
                    return value;
                }
            }
            if (Config.I.isOnlineTranslate()) {
                String md5 = Byte.getMd5(filePath + "=>" + source);
                if (TranslateMap.get(md5) == null) {
                    TranslateMap.put(md5, new data(filePath, source));
                }
            }else {
                Thread thread = new Thread(()-> add(filePath,source,source));
                thread.start();
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return value;
    }

    public static void addDef(){
        if (TranslateMap.isEmpty()){
            return;
        }
        Map.Entry<String, data> firstEntry = TranslateMap.entrySet().stream().findFirst().get();
        /*
        TranslateMap.forEach((s, data) -> add(data.filePath,data.source,
                 getTranslate(data.source)));

         */
        data data = firstEntry.getValue();
        add(data.filePath,data.source,getTranslate(data.source));
        TranslateMap.remove(firstEntry.getKey());
    }
    public static void add(String filePath,String source,String tran){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(filePath);
        JsonObject jsonObject = new JsonObject();
        try {
            if (file.exists()) {
                FileReader fileReader = new FileReader(filePath);
                jsonObject = gson.fromJson(fileReader, JsonObject.class);
            } else {
                file.createNewFile();
            }
            jsonObject.addProperty(source, tran);
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                gson.toJson(jsonObject, fileWriter);
            }
        }catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
    public static void start(){
        if (Config.I.isOnlineTranslate()){
            if (tt == null) {
                tt = new Thread(() -> {
                    while (true) {
                        addDef();
                        LOGGER.debug("剩余未翻译数量:" + TranslateMap.size());
                        try {
                            Thread.sleep(Config.I.getDatas().getRequestInterval());
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                tt.start();
            }
        }
    }
    public static @NotNull String getST() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length >= 4) {
            String callerClassName = stackTrace[3].getClassName();
            try {
                Class<?> callerClass = Class.forName(callerClassName);
                Package callerPackage = callerClass.getPackage();
                if (callerPackage != null) {
                    return callerPackage.getName();
                }
            } catch (ClassNotFoundException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return "unknown";

    }

    public record data(String filePath, String source) {
    }
}
