package mods.tutorial;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraft.client.renderer.texture.IconRegister;

public class ItemSandwich extends Item 
{

	public ItemSandwich(int par1) 
	{
		super(par1);
		this.setCreativeTab(CreativeTabs.tabFood);
	}
	
	
	@Override
	public void updateIcons(IconRegister iconRegister)
	{
	         iconIndex = iconRegister.registerIcon("tutorial:itemSandwitch");
	}
}


