package squeek.harvestcraftwaila;

import net.minecraftforge.common.MinecraftForge;
import squeek.harvestcraftwaila.fixers.IconFixer;
import squeek.harvestcraftwaila.fixers.NameFixer;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = ModInfo.MODID, version = ModInfo.VERSION, dependencies = "after:pamharvestcraft;after:pamweeeflowers;after:HungerOverhaul")
public class ModHarvestCraftWaila
{
	public static boolean hasHarvestCraft = false;
	public static boolean hasWeeeFlowers = false;
	
	@SideOnly(Side.CLIENT)
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		hasHarvestCraft = Loader.isModLoaded("pamharvestcraft");
		hasWeeeFlowers = Loader.isModLoaded("pamweeeflowers");
	}
	
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
