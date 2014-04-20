package squeek.harvestcraftwaila.fixers;

import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import assets.pamharvestcraft.PamHarvestCraft;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class IconFixer
{
	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void onPostTextureStitch(TextureStitchEvent.Post event)
	{
		try
		{
			Icon icon = Block.crops.getIcon(0, 0);
			ReflectionHelper.setPrivateValue(Block.class, PamHarvestCraft.pamCrop, icon, ObfuscationReflectionHelper.remapFieldNames(Block.class.getName(), "blockIcon", "field_94336_cN", "cW"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
