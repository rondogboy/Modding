package mods.tutorial;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="tutorial", name="Tutorial Mod", version="0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class TutorialMain 
{
	@Instance("TutorialMain")
	public static TutorialMain instance;
	
	@SidedProxy(clientSide = "mods.tutorial.ClientProxyTutorial", serverSide = "mods.tutorial.CommonProxyTutorial")
	public static CommonProxyTutorial proxy;
	
	static int startEntityId = 300;
	
	@Init
	public void load(FMLInitializationEvent event) 
	{
		proxy.registerRenderThings();
		
		Block tutorialBlock = new BlockTutorialBlock(250, 0).setUnlocalizedName("tutorialBlock");  
		GameRegistry.registerBlock(tutorialBlock);
		LanguageRegistry.addName(tutorialBlock, "Tutorial Block");
		
		Item itemSandwich = new ItemSandwich(500).setUnlocalizedName("itemSandwich");
		LanguageRegistry.addName(itemSandwich, "Sandwich");
		
		Item itemIcecream = new ItemIcecream(501).setUnlocalizedName("itemIcecream");
		LanguageRegistry.addName(itemIcecream, "IceCream");
		
		EntityRegistry.registerModEntity(EntityIceCream.class, "IceCream", 1, this, 40, 1, true);
		EntityRegistry.addSpawn(EntityIceCream.class, 2, 2, 4, EnumCreatureType.creature, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver, BiomeGenBase.iceMountains, BiomeGenBase.icePlains, BiomeGenBase.taiga, BiomeGenBase.taigaHills);
		LanguageRegistry.instance().addStringLocalization("entity.tutorial.EntityIceCream.name", "IceCream");
		registerEntityEgg(EntityIceCream.class, 0xffffff, 0xF71414);
		
		EntityRegistry.registerModEntity(EntitySquidMob.class, "SquidMob", 2, this, 40, 1, true);
		EntityRegistry.addSpawn(EntitySquidMob.class, 2, 2, 4, EnumCreatureType.creature, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver, BiomeGenBase.iceMountains, BiomeGenBase.icePlains, BiomeGenBase.taiga, BiomeGenBase.taigaHills);
		LanguageRegistry.instance().addStringLocalization("entity.tutorial.EntitySquidMob.name", "SquidMob");
		registerEntityEgg(EntitySquidMob.class, 0xffffff, 0x000000);
		
		GameRegistry.addRecipe(new ItemStack(tutorialBlock,1), "III", "III", "III", 'I', new ItemStack(itemIcecream));
		GameRegistry.addRecipe(new ItemStack(itemSandwich,1), " B ", " M ", " B ", 'B', new ItemStack(Item.bread), 'M', new ItemStack(Item.beefCooked));
		//GameRegistry.addSmelting(itemSandwich.itemID, new ItemStack(Block.dirt), 0.0F);
	
		
	}
	
	public static int getUniqueEntityId() 
	{
		do 
		{
			startEntityId++;
		} 
		while (EntityList.getStringFromID(startEntityId) != null);

		return startEntityId;
	}
	
	public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) 
	{
		int id = getUniqueEntityId();
		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityEggInfo(id, primaryColor, secondaryColor));
	}

}