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
	private boolean hasSetIcon = false;

	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void onPostTextureStitch(TextureStitchEvent.Post event)
	{
		if (!hasSetIcon)
		{
			try
			{
				Icon icon = Block.crops.getIcon(0, 0);
				ReflectionHelper.setPrivateValue(Block.class, PamHarvestCraft.pamCrop, icon, ObfuscationReflectionHelper.remapFieldNames(Block.class.getName(), "blockIcon", "field_94336_cN", "cW"));
				hasSetIcon = true;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
