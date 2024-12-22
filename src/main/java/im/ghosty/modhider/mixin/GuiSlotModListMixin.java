package im.ghosty.modhider.mixin;

import im.ghosty.modhider.utils.MHConfig;
import im.ghosty.modhider.utils.ModUtils;
import net.minecraftforge.fml.client.GuiSlotModList;
import net.minecraftforge.fml.common.ModContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = GuiSlotModList.class, remap = false)
public class GuiSlotModListMixin {
	
	@Unique
	private String modHider$prefix;
	
	@ModifyVariable(method = "drawSlot", at = @At("STORE"), ordinal = 0)
	private ModContainer getModID(ModContainer container) {
		String modid = container.getModId();
		
		if(ModUtils.shouldList(modid)) {
			if (MHConfig.hidden.contains(modid))
				modHider$prefix = "§c✖ §r";
			else
				modHider$prefix = "§a✔ §r";
		} else
			modHider$prefix = "";
		
		return container;
	}
	
	@ModifyVariable(method = "drawSlot", at = @At("STORE"), ordinal = 0)
	private String drawModName(String name) {
		return modHider$prefix + name;
	}
	
}
