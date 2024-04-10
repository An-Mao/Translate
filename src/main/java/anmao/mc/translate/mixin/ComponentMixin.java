package anmao.mc.translate.mixin;

import anmao.mc.translate.translate.DB;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Component.class)
public interface ComponentMixin {
    /**
     * @author AnMaos
     * @reason translate
     */
    @Overwrite
    static MutableComponent literal(String text){
        return MutableComponent.create(new LiteralContents(DB.get(DB.getST(),text)));
    }
}
