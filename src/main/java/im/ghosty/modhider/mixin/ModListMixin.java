package im.ghosty.modhider.mixin;

import im.ghosty.modhider.utils.MHConfig;
import im.ghosty.modhider.utils.ModUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(value = FMLHandshakeMessage.ModList.class, remap = false)
public class ModListMixin {
	
	@Shadow
	private Map<String, String> modTags;
	
	@Inject(method = "<init>(Ljava/util/List;)V", at = @At("RETURN"))
	public void removeMods(List<ModContainer> modList, CallbackInfo ci) {
		if (Minecraft.getMinecraft().isIntegratedServerRunning()) // singleplayer check
			return;
		
		// We remove this mod from the list.
		// This prevent from people forgetting to remove it
		// and servers possibly flagging that.
		modTags.remove("modhider");
		
		// We then just remove all mods we chose to
		MHConfig.hidden.forEach(mod -> {
			if(!ModUtils.isForgeMod(mod))
				modTags.remove(mod);
		});
	}
	
}
