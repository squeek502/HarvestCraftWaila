package squeek.harvestcraftwaila.fixers;

import squeek.harvestcraftwaila.ModHarvestCraftWaila;
import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import assets.pamharvestcraft.BlockPamCrop;
import assets.pamharvestcraft.PamHarvestCraft;
import assets.pamweeeflowers.BlockPamFlower;
import assets.pamweeeflowers.BlockPamFlowerCrop;
import assets.pamweeeflowers.BlockPamMoonFlower;
import assets.pamweeeflowers.PamWeeeFlowers;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class IconFixer
{
	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void onPostTextureStitch(TextureStitchEvent.Post event)
	{
		if (ModHarvestCraftWaila.hasHarvestCraft)
		{
			if (PamHarvestCraft.pamCrop != null && ((BlockPamCrop) PamHarvestCraft.pamCrop).iconArray != null)
			{
				setBlockIcon(PamHarvestCraft.pamCrop, ((BlockPamCrop) PamHarvestCraft.pamCrop).iconArray[0][0]);
			}
		}

		if (ModHarvestCraftWaila.hasWeeeFlowers)
		{
			if (PamWeeeFlowers.pamFlower != null && ((BlockPamFlower) PamWeeeFlowers.pamFlower).icons != null)
			{
				setBlockIcon(PamWeeeFlowers.pamFlower, ((BlockPamFlower) PamWeeeFlowers.pamFlower).icons[0]);
			}
	
			if (PamWeeeFlowers.pamflowerCrop != null && ((BlockPamFlowerCrop) PamWeeeFlowers.pamflowerCrop).iconArray != null)
			{
				setBlockIcon(PamWeeeFlowers.pamflowerCrop, ((BlockPamFlowerCrop) PamWeeeFlowers.pamflowerCrop).iconArray[0][0]);
			}
	
			if (PamWeeeFlowers.pammoonFlower != null && ((BlockPamMoonFlower) PamWeeeFlowers.pammoonFlower).icons != null)
			{
				setBlockIcon(PamWeeeFlowers.pammoonFlower, ((BlockPamMoonFlower) PamWeeeFlowers.pammoonFlower).icons[0]);
			}
		}
	}

	public void setBlockIcon(Block block, Icon icon)
	{
		if (block == null || icon == null)
			return;

		try
		{
			ReflectionHelper.setPrivateValue(Block.class, block, icon, ObfuscationReflectionHelper.remapFieldNames(Block.class.getName(), "blockIcon", "field_94336_cN", "cW"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
