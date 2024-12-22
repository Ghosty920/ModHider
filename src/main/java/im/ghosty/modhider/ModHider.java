package im.ghosty.modhider;

import im.ghosty.modhider.utils.MHConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(name = "ModHider", modid = "modhider", version = "1.0.0", clientSideOnly = true)
public final class ModHider {
	
	public static final Logger logger = LogManager.getLogger();
	
	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event) {
		MHConfig.load();
	}
	
}
