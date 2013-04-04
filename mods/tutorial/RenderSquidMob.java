package mods.tutorial;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class RenderSquidMob extends RenderLiving 
{
	protected ModelSquidMob model;
	 
	public RenderSquidMob (ModelSquidMob modelSquidMob, float f)
	{
		super(modelSquidMob, f);
		model = ((ModelSquidMob)mainModel);
	}
	
	public void renderSquidMob(EntitySquidMob entity, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }
 
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        renderSquidMob((EntitySquidMob)par1EntityLiving, par2, par4, par6, par8, par9);
    }
 
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        renderSquidMob((EntitySquidMob)par1Entity, par2, par4, par6, par8, par9);
    }

}