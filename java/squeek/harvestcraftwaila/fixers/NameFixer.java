package squeek.harvestcraftwaila.fixers;

import java.lang.reflect.Field;
import net.minecraft.block.Block;
import assets.pamharvestcraft.BlockPamFruit;
import assets.pamharvestcraft.PamHarvestCraft;

public class NameFixer
{
	public static final Block fruits[] = new Block[]{
	PamHarvestCraft.pamApple, PamHarvestCraft.pamAvocado, PamHarvestCraft.pamBanana, PamHarvestCraft.pamCherry,
	PamHarvestCraft.pamCinnamon, PamHarvestCraft.pamCoconut, PamHarvestCraft.pamDragonfruit, PamHarvestCraft.pamLemon,
	PamHarvestCraft.pamLime, PamHarvestCraft.pamMango, PamHarvestCraft.pamNutmeg, PamHarvestCraft.pamOlive,
	PamHarvestCraft.pamOrange, PamHarvestCraft.pamPapaya, PamHarvestCraft.pamPeach, PamHarvestCraft.pamPear,
	PamHarvestCraft.pamPeppercorn, PamHarvestCraft.pamPlum, PamHarvestCraft.pamPomegranate, PamHarvestCraft.pamStarfruit,
	PamHarvestCraft.pamVanillabean, PamHarvestCraft.pamWalnut
	};

	private static Field blockFruit_fruit = null;

	public static void init()
	{
		try
		{
			blockFruit_fruit = BlockPamFruit.class.getDeclaredField("fruit");
			blockFruit_fruit.setAccessible(true);

			fixFruitNames();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void fixFruitNames()
	{
		for (Block fruit : fruits)
		{
			try
			{
				BlockPamFruit blockFruit = (BlockPamFruit) fruit;
				blockFruit.setUnlocalizedName((String) blockFruit_fruit.get(blockFruit));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
