package mods.tutorial;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class RenderIceCream extends RenderLiving 
{
	protected ModelIceCream model;
	 
	public RenderIceCream (ModelIceCream modelIceCream, float f)
	{
		super(modelIceCream, f);
		model = ((ModelIceCream)mainModel);
	}
	
	public void renderIceCream(EntityIceCream entity, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }
 
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        renderIceCream((EntityIceCream)par1EntityLiving, par2, par4, par6, par8, par9);
    }
 
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        renderIceCream((EntityIceCream)par1Entity, par2, par4, par6, par8, par9);
    }

}
