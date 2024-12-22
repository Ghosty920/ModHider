package im.ghosty.modhider.utils;

import im.ghosty.modhider.ModHider;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import net.minecraftforge.fml.common.Loader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

@UtilityClass
public final class MHConfig {
	
	private static final File configFile = new File(Loader.instance().getConfigDir(), "modhider.txt");
	
	/**
	 * The list of all mod ids to be hidden from servers
	 */
	public static final HashSet<String> hidden = new HashSet<>();
	
	@SneakyThrows
	public static void load() {
		if(!configFile.exists())
			configFile.createNewFile();
		List<String> mods = FileUtils.readLines(configFile, StandardCharsets.UTF_8);
		setHidden(mods);
		ModHider.logger.info("Loaded {} hidden mods.", mods.size());
	}
	
	@SneakyThrows
	public static void save() {
		if(!configFile.exists())
			configFile.createNewFile();
		FileUtils.writeLines(configFile, "UTF-8", hidden, false);
		ModHider.logger.info("Saved {} hidden mods.", hidden.size());
	}
	
	public static void setHidden(Collection<String> toHide) {
		hidden.clear();
		hidden.addAll(toHide);
	}
	
}
