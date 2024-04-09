package anmao.mc.translate;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScriptSupport {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static void runJS(){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        try {
            engine.put("source", 30);
            Object result = engine.eval("var x = javaVariable; var y = 20; x + y;");
            System.out.println("Result: " + result);
        } catch (ScriptException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
