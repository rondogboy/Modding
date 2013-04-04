package mods.tutorial;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockTutorialBlock extends Block
{
	 private Icon IceTop;
	 private Icon IceSide;
	 private Icon IceBottom;
	
	public BlockTutorialBlock(int id, int texture)
	{
	  super(id, Material.cloth);
	  this.setCreativeTab(CreativeTabs.tabBlock);
	  this.setStepSound(soundClothFootstep);
	}	
	
	@Override
	public void registerIcons(IconRegister par1IconRegister)
    {
            this.IceTop = par1IconRegister.registerIcon("tutorial:IceTop");
            this.IceBottom = par1IconRegister.registerIcon("tutorial:IceBottom");
            this.IceSide = par1IconRegister.registerIcon("tutorial:IceSide");
    }
   
	@Override
	public Icon getBlockTextureFromSideAndMetadata(int side, int metadata)
    {
    	if (side == 0) //bottom
    	{
    		return this.IceBottom;     
    	}
    	if (side==1) //top
    	{
    		return this.IceTop;
        }
    	else //sides
    	{
    		return this.IceSide;
    	}
    }
    	
    /*public void breakBlock(World world, int x, int y, int z, int i, int j)
    {
    	dropItems(world, x, y, z);
    	super.breakBlock(world, x, y, z, i, j);
    }
  
    private void dropItems(World world, int x, int y, int z)
    {
    	Random rand = new Random();
    	TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
    	
    	if(!(tile_entity instanceof IInventory))
    	{
    		return;
    	}
    	
    	IInventory inventory = (IInventory) tile_entity;
    	
    	for(int i = 0; i < inventory.getSizeInventory(); i++)
    	{
    		ItemStack item = inventory.getStackInSlot(i);
    		if(item != null && item.stackSize > 0)
    		{
    			float rx = rand.nextFloat() * 0.6F + 0.1F;
    			float ry = rand.nextFloat() * 0.6F + 0.1F;
    			float rz = rand.nextFloat() * 0.6F + 0.1F;
    			
    			EntityItem entity_item = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));
                  
    			if(item.hasTagCompound())
    			{
    				entity_item.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
    			}

    			float factor = 0.5F;

    			entity_item.motionX = rand.nextGaussian() * factor;
    			entity_item.motionY = rand.nextGaussian() * factor + 0.2F;
    			entity_item.motionZ = rand.nextGaussian() * factor;
    			world.spawnEntityInWorld(entity_item);
    			item.stackSize = 0;
    		}
    	}
    }*/
}
