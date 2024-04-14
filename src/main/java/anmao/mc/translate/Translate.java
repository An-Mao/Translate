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
