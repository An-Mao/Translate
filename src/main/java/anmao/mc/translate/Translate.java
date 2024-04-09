package anmao.mc.translate;

import net.minecraftforge.fml.common.Mod;


@Mod(Translate.MOD_ID)
public class Translate
{
    public static final String MOD_ID = "translate";
    public Translate() {
        JavaScriptSupport.runJS();
    }
}
