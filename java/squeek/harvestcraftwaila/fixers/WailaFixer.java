package squeek.harvestcraftwaila.fixers;

import java.util.List;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import squeek.harvestcraftwaila.ModHarvestCraftWaila;
import assets.pamharvestcraft.BlockPamCrop;
import assets.pamharvestcraft.BlockPamFruit;
import assets.pamharvestcraft.PamHarvestCraft;
import assets.pamharvestcraft.TileEntityPamCrop;
import assets.pamweeeflowers.BlockPamFlowerCrop;
import assets.pamweeeflowers.PamWeeeFlowers;
import assets.pamweeeflowers.TileEntityPamFlowerCrop;

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
		if (ModHarvestCraftWaila.hasHarvestCraft)
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
					if (growthValue < 100)
						toolTip.add(String.format("%s : %.0f %%", StatCollector.translateToLocal("hud.msg.growth"), growthValue));
					else
						toolTip.add(String.format("%s : %s", StatCollector.translateToLocal("hud.msg.growth"), StatCollector.translateToLocal("hud.msg.mature")));
				}
			}
		}
		if (ModHarvestCraftWaila.hasWeeeFlowers)
		{
			if (accessor.getTileEntity() instanceof TileEntityPamFlowerCrop)
			{
				TileEntityPamFlowerCrop tileCrop = (TileEntityPamFlowerCrop) accessor.getTileEntity();
				ItemStack itemStackCrop;
				if (tileCrop.cropID != 4 && tileCrop.cropID != 14)
					itemStackCrop = new ItemStack(PamWeeeFlowers.pamFlower, 1, tileCrop.cropID);
				else
					itemStackCrop = new ItemStack(tileCrop.cropID == 4 ? Block.plantYellow : Block.plantRed);
				toolTip.add(itemStackCrop.getDisplayName());

				if (config.getConfig("general.showcrop"))
				{
					float growthValue = (tileCrop.getGrowthStage() / 2.0F) * 100.0F;
					if (growthValue < 100)
						toolTip.add(String.format("%s : %.0f %%", StatCollector.translateToLocal("hud.msg.growth"), growthValue));
					else
						toolTip.add(String.format("%s : %s", StatCollector.translateToLocal("hud.msg.growth"), StatCollector.translateToLocal("hud.msg.mature")));
				}
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
		if (ModHarvestCraftWaila.hasHarvestCraft)
		{
			registrar.registerBodyProvider(instance, BlockPamCrop.class);
			registrar.registerBodyProvider(instance, BlockPamFruit.class);
		}
		if (ModHarvestCraftWaila.hasWeeeFlowers)
		{
			registrar.registerBodyProvider(instance, BlockPamFlowerCrop.class);
		}
	}
}