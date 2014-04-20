package squeek.harvestcraftwaila.fixers;

import java.util.List;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import assets.pamharvestcraft.BlockPamCrop;
import assets.pamharvestcraft.BlockPamFruit;
import assets.pamharvestcraft.PamHarvestCraft;
import assets.pamharvestcraft.TileEntityPamCrop;

public class WailaFixer implements IWailaDataProvider
{
	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> toolTip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return toolTip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> toolTip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		if (accessor.getTileEntity() instanceof TileEntityPamCrop)
		{
			TileEntityPamCrop tileCrop = (TileEntityPamCrop) accessor.getTileEntity();
			ItemStack itemStackCrop = new ItemStack(PamHarvestCraft.PamCropItems[tileCrop.cropID], 1, 0);
			toolTip.add(itemStackCrop.getDisplayName());
		}
		if (accessor.getBlock() instanceof BlockPamFruit)
		{
			if (config.getConfig("general.showcrop"))
			{
				float growthValue = (accessor.getMetadata() / 2.0F) * 100.0F;
				if (growthValue < 100.0)
					toolTip.add(String.format("%s : %.0f %%", StatCollector.translateToLocal("hud.msg.growth"), growthValue));
				else
					toolTip.add(String.format("%s : %s", StatCollector.translateToLocal("hud.msg.growth"), StatCollector.translateToLocal("hud.msg.mature")));
			}
		}
		return toolTip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> toolTip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return toolTip;
	}

	public static void callbackRegister(IWailaRegistrar registrar)
	{
		WailaFixer instance = new WailaFixer();
		registrar.registerBodyProvider(instance, BlockPamCrop.class);
		registrar.registerBodyProvider(instance, BlockPamFruit.class);
	}
}