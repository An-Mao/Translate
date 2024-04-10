package anmao.mc.translate;

import anmao.mc.translate.translate.DB;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod(Translate.MOD_ID)
public class Translate
{
    public static final String MOD_ID = "translate";
    public Translate() {
        System.out.println("test :: "+ Component.literal("test 1"));

        /*
        Object result = EasyJS.creat()
                .addParameter("source","test")
                .runFile(Configs.ConfigDir_JS+"baidu.js");

        String re =  Network.sendData("https://fanyi-api.baidu.com/api/trans/vip/translate","POST","Content-Type:application/x-www-form-urlencoded;",result.toString());
        System.out.println("result2::"+URLDecoder.decode("\\u6d4b\\u9a8c", StandardCharsets.UTF_8));
        System.out.println("result::"+re);
        result = EasyJS.creat()
                .addParameter("translate",re)
                .runFile(Configs.ConfigDir_JS+"baiduR.js");
        System.out.println("result5::"+result);
        System.exit(0);

         */
    }
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            DB.start();
        }
    }
}
