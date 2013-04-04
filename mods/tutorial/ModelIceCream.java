package mods.tutorial;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelIceCream extends ModelBase
{
	//fields
	ModelRenderer CreamMain;
	ModelRenderer Cream1;
    ModelRenderer Cream2;
    ModelRenderer Cream3;
    ModelRenderer ConeTop;
    ModelRenderer Cone1;
    ModelRenderer Cone2;
    ModelRenderer Cone3;
    ModelRenderer Cherry;
  
    public ModelIceCream()
    {
    	textureWidth = 64;
    	textureHeight = 64;
    	
    	CreamMain = new ModelRenderer(this, 0, 0);
    	CreamMain.addBox(-5F, -2F, -5F, 10, 6, 10);
    	CreamMain.setRotationPoint(0F, 0F, 0F);
    	CreamMain.setTextureSize(64, 64);
    	CreamMain.mirror = true;
    	setRotation(CreamMain, 0F, 0F, 0F);
    	Cream1 = new ModelRenderer(this, 0, 17);
    	Cream1.addBox(-4F, -4F, -4F, 8, 2, 8);
    	Cream1.setRotationPoint(0F, 0F, 0F);
    	Cream1.setTextureSize(64, 64);
    	Cream1.mirror = true;
    	setRotation(Cream1, 0F, 0F, 0F);
    	Cream2 = new ModelRenderer(this, 0, 28);
    	Cream2.addBox(-3F, -5F, -3F, 6, 1, 6);
    	Cream2.setRotationPoint(0F, 0F, 0F);
    	Cream2.setTextureSize(64, 64);
    	Cream2.mirror = true;
    	setRotation(Cream2, 0F, 0F, 0F);
    	Cream3 = new ModelRenderer(this, 0, 36);
    	Cream3.addBox(-2F, -6F, -2F, 4, 1, 4);
    	Cream3.setRotationPoint(0F, 0F, 0F);
    	Cream3.setTextureSize(64, 64);
    	Cream3.mirror = true;
    	setRotation(Cream3, 0F, 0F, 0F);
    	ConeTop = new ModelRenderer(this, 32, 55);
    	ConeTop.addBox(-4F, 4F, -4F, 8, 1, 8);
    	ConeTop.setRotationPoint(0F, 0F, 0F);
    	ConeTop.setTextureSize(64, 64);
    	ConeTop.mirror = true;
    	setRotation(ConeTop, 0F, 0F, 0F);
    	Cone1 = new ModelRenderer(this, 40, 43);
    	Cone1.addBox(-3F, 5F, -3F, 6, 5, 6);
    	Cone1.setRotationPoint(0F, 0F, 0F);
    	Cone1.setTextureSize(64, 64);
    	Cone1.mirror = true;
    	setRotation(Cone1, 0F, 0F, 0F);
    	Cone2 = new ModelRenderer(this, 48, 33);
    	Cone2.addBox(-2F, 10F, -2F, 4, 5, 4);
    	Cone2.setRotationPoint(0F, 0F, 0F);
    	Cone2.setTextureSize(64, 64);
    	Cone2.mirror = true;
    	setRotation(Cone2, 0F, 0F, 0F);
    	Cone3 = new ModelRenderer(this, 56, 26);
    	Cone3.addBox(-1F, 15F, -1F, 2, 4, 2);
    	Cone3.setRotationPoint(0F, 0F, 0F);
    	Cone3.setTextureSize(64, 64);
    	Cone3.mirror = true;
    	setRotation(Cone3, 0F, 0F, 0F);
    	Cherry = new ModelRenderer(this, 0, 41);
    	Cherry.addBox(0F, -7F, 0F, 1, 1, 1);
    	Cherry.setRotationPoint(0F, 0F, 0F);
    	Cherry.setTextureSize(64, 64);
    	Cherry.mirror = true;
    	setRotation(Cherry, 0F, 0F, 0F);
    }
  
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
      super.render(par1Entity, par2, par3, par4, par5, par6, par7);
      setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
      CreamMain.render(par7);
      Cream1.render(par7);
      Cream2.render(par7);
      Cream3.render(par7);
      ConeTop.render(par7);
      Cone1.render(par7);
      Cone2.render(par7);
      Cone3.render(par7);
      Cherry.render(par7);
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
