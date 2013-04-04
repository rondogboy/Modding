package mods.tutorial;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelSquidMob extends ModelBase
{
  //fields
    ModelRenderer Body;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Leg5;
    ModelRenderer Leg6;
    ModelRenderer Leg7;
    ModelRenderer Leg8;
  
  public ModelSquidMob()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Body = new ModelRenderer(this, 0, 0);
      Body.addBox(0F, 0F, 0F, 12, 16, 12);
      Body.setRotationPoint(-6F, -8F, -6F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      Leg1 = new ModelRenderer(this, 48, 0);
      Leg1.addBox(-1F, 0F, -1F, 2, 18, 2);
      Leg1.setRotationPoint(0F, 7F, -5F);
      Leg1.setTextureSize(64, 32);
      Leg1.mirror = true;
      setRotation(Leg1, -0.3717861F, 0F, 0F);
      Leg2 = new ModelRenderer(this, 48, 0);
      Leg2.addBox(-1F, 0F, -1F, 2, 18, 2);
      Leg2.setRotationPoint(-4F, 7F, -4F);
      Leg2.setTextureSize(64, 32);
      Leg2.mirror = true;
      setRotation(Leg2, -0.3717861F, 0.7853982F, 0F);
      Leg3 = new ModelRenderer(this, 48, 0);
      Leg3.addBox(-1F, 0F, -1F, 2, 18, 2);
      Leg3.setRotationPoint(-5F, 7F, 0F);
      Leg3.setTextureSize(64, 32);
      Leg3.mirror = true;
      setRotation(Leg3, -0.3665191F, 1.570796F, 0F);
      Leg4 = new ModelRenderer(this, 48, 0);
      Leg4.addBox(-1F, 0F, -1F, 2, 18, 2);
      Leg4.setRotationPoint(-4F, 7F, 4F);
      Leg4.setTextureSize(64, 32);
      Leg4.mirror = true;
      setRotation(Leg4, -0.3717861F, 2.356194F, 0F);
      Leg5 = new ModelRenderer(this, 48, 0);
      Leg5.addBox(-1F, 0F, -1F, 2, 18, 2);
      Leg5.setRotationPoint(0F, 7F, 5F);
      Leg5.setTextureSize(64, 32);
      Leg5.mirror = true;
      setRotation(Leg5, -0.3665191F, 3.141593F, 0F);
      Leg6 = new ModelRenderer(this, 48, 0);
      Leg6.addBox(-1F, 0F, -1F, 2, 18, 2);
      Leg6.setRotationPoint(4F, 7F, 4F);
      Leg6.setTextureSize(64, 32);
      Leg6.mirror = true;
      setRotation(Leg6, -0.3717861F, 3.926991F, 0F);
      Leg7 = new ModelRenderer(this, 48, 0);
      Leg7.addBox(-1F, 0F, -1F, 2, 18, 2);
      Leg7.setRotationPoint(5F, 7F, 0F);
      Leg7.setTextureSize(64, 32);
      Leg7.mirror = true;
      setRotation(Leg7, -0.3665191F, 4.712389F, 0F);
      Leg8 = new ModelRenderer(this, 48, 0);
      Leg8.addBox(-1F, 0F, -1F, 2, 18, 2);
      Leg8.setRotationPoint(4F, 7F, -4F);
      Leg8.setTextureSize(64, 32);
      Leg8.mirror = true;
      setRotation(Leg8, -0.3717861F, 5.497787F, 0F);
  }
  
  public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
  {
    super.render(par1Entity, par2, par3, par4, par5, par6, par7);
    setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
    Body.render(par7);
    Leg1.render(par7);
    Leg2.render(par7);
    Leg3.render(par7);
    Leg4.render(par7);
    Leg5.render(par7);
    Leg6.render(par7);
    Leg7.render(par7);
    Leg8.render(par7);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
  {
  	super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
  }

}