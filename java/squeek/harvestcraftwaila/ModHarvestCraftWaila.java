package squeek.harvestcraftwaila;

import net.minecraftforge.common.MinecraftForge;
import squeek.harvestcraftwaila.ModInfo;
import squeek.harvestcraftwaila.fixers.IconFixer;
import squeek.harvestcraftwaila.fixers.NameFixer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = ModInfo.MODID, version = ModInfo.VERSION, dependencies = "required-after:pamharvestcraft;after:HungerOverhaul")
public class ModHarvestCraftWaila
{
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
