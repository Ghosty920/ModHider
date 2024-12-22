package im.ghosty.modhider.mixin;

import im.ghosty.modhider.utils.MHConfig;
import im.ghosty.modhider.utils.ModUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.GuiModList;
import net.minecraftforge.fml.client.config.GuiCheckBox;
import net.minecraftforge.fml.common.ModContainer;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiModList.class)
public class GuiModListMixin extends GuiScreen {
	
	@Shadow
	private int listWidth;
	
	@Shadow
	private ModContainer selectedMod;
	
	@Unique
	private GuiCheckBox modHider$buttonHide;
	
	@Inject(method = "initGui", at = @At("RETURN"))
	public void initGui(CallbackInfo ci) {
		MHConfig.load();
		modHider$buttonHide = new GuiCheckBox(969, listWidth + 20, height - 20, "Hide", false);
		modHider$buttonHide.visible = false; // We haven't chosen a mod yet
		buttonList.add(modHider$buttonHide);
	}
	
	@Inject(method = "selectModIndex", at = @At("RETURN"), remap = false)
	public void selectModIndex(int index, CallbackInfo ci) {
		String modId = selectedMod.getModId();
		if (ModUtils.shouldList(modId)) {
			modHider$buttonHide.visible = true;
			modHider$buttonHide.setIsChecked(MHConfig.hidden.contains(modId));
		} else
			modHider$buttonHide.visible = false;
	}
	
	@Inject(method = "actionPerformed", at = @At("HEAD"))
	public void actionPerformed(GuiButton button, CallbackInfo ci) {
		if (button.id != 969 || !(button instanceof GuiCheckBox) || selectedMod == null)
			return;
		
		GuiCheckBox btn = (GuiCheckBox) button;
		boolean hidden = btn.isChecked();
		if (hidden)
			MHConfig.hidden.add(selectedMod.getModId());
		else
			MHConfig.hidden.remove(selectedMod.getModId());
		
		MHConfig.save();
	}
	
}
