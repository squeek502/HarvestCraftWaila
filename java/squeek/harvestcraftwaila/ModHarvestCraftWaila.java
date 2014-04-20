package squeek.harvestcraftwaila;

import net.minecraftforge.common.MinecraftForge;
import squeek.harvestcraftwaila.fixers.IconFixer;
import squeek.harvestcraftwaila.fixers.NameFixer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
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
		FMLInterModComms.sendMessage("Waila", "register", "squeek.harvestcraftwaila.fixers.WailaFixer.callbackRegister");
		NameFixer.init();
	}

	@SideOnly(Side.CLIENT)
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new IconFixer());
	}
}
