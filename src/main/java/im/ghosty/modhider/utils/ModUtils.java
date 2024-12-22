package im.ghosty.modhider.utils;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public final class ModUtils {
	
	/**
	 * Check if the mod id given is corresponding to one used by Forge.
	 * <p>
	 * Removing them could insta-flag some servers/anticheats, so that's a really bad idea :(
	 *
	 * @param modid The mod id to check
	 * @return If it is a mod id related to Forge
	 */
	public static boolean isForgeMod(@NotNull String modid) {
		return modid.equals("Forge")
			|| modid.equals("FML")
			|| modid.equals("mcp");
	}
	
	/**
	 * Check if the mod id given should have the icon in {@link im.ghosty.modhider.mixin.GuiSlotModListMixin} or not.
	 * <p>
	 * Mod ids excluded are Forge ones and {@code modhider}
	 *
	 * @param modid The mod id to check
	 * @return If it is a mod id related to Forge, or "modhider"
	 */
	public static boolean shouldList(@NotNull String modid) {
		return !modid.equals("modhider")
			&& !isForgeMod(modid);
	}
	
}
