package squeek.harvestcraftwaila;

import java.lang.reflect.Field;
import java.util.List;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.item.ItemStack;
import assets.pamharvestcraft.BlockPamCrop;
import assets.pamharvestcraft.BlockPamFruit;
import assets.pamharvestcraft.TileEntityPamCrop;
import static mcp.mobius.waila.api.SpecialChars.*;

public class WailaProvider implements IWailaDataProvider
{
	private static Field fruit = null;
	
    @Override
    public ItemStack getWailaStack(IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler)
    {
        if (iWailaDataAccessor.getBlock() instanceof BlockPamFruit)
        {
	    	BlockPamFruit blockFruit = (BlockPamFruit) iWailaDataAccessor.getBlock();
	    	ItemStack dropFruit = new ItemStack(blockFruit.getTreeDrop(), 0, 0);
	    	return dropFruit;
        }
    	
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> strings, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler)
    {
        if (iWailaDataAccessor.getBlock() instanceof BlockPamFruit)
        {
        	BlockPamFruit blockFruit = (BlockPamFruit) iWailaDataAccessor.getBlock();
        	ItemStack dropFruit = new ItemStack(blockFruit.getTreeDrop(), 0, 0);
        	strings.clear();
        	strings.add(WHITE + dropFruit.getDisplayName());
        }
        return strings;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> strings, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler)
    {
        if (iWailaDataAccessor.getTileEntity() instanceof TileEntityPamCrop)
        {
        	TileEntityPamCrop tileCrop = (TileEntityPamCrop) iWailaDataAccessor.getTileEntity();
        	BlockPamCrop blockCrop = (BlockPamCrop) iWailaDataAccessor.getBlock();
        	strings.add("Crop: "+blockCrop.cropName[tileCrop.cropID]);
        }
        if (iWailaDataAccessor.getBlock() instanceof BlockPamFruit)
        {
        	BlockPamFruit blockFruit = (BlockPamFruit) iWailaDataAccessor.getBlock();
        	ItemStack dropFruit = new ItemStack(blockFruit.getTreeDrop(), 0, 0);
			strings.add("Drop fruit: "+dropFruit.getDisplayName());
			strings.add("Tree drop: "+blockFruit.getTreeDrop());
        }
        return strings;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> strings, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler)
    {
        return strings;
    }

    public static void callbackRegister(IWailaRegistrar registrar)
    {
        WailaProvider instance = new WailaProvider();
        registrar.registerBodyProvider(instance, BlockPamCrop.class);
        registrar.registerStackProvider(instance, BlockPamFruit.class);
        //registrar.registerHeadProvider(instance, BlockPamFruit.class);
        //registrar.registerBodyProvider(instance, BlockPamFruit.class);
        
        try
        {
        	fruit = BlockPamFruit.class.getDeclaredField("fruit");
        	fruit.setAccessible(true);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }
}