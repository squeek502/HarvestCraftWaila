package squeek.harvestcraftwaila;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = ModHarvestCraftWaila.MODID, version = ModHarvestCraftWaila.VERSION, dependencies = "required-after:pamharvestcraft")
public class ModHarvestCraftWaila
{
	public static final String MODID = "HarvestCraftWaila";
	public static final String VERSION = "${version}";

	@SideOnly(Side.CLIENT)
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		FMLInterModComms.sendMessage("Waila", "register", "squeek.harvestcraftwaila.WailaProvider.callbackRegister");
	}
}
