package mods.tutorial;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.stats.AchievementList;

/*public class EntityIceCream extends EntityMob
{
	public EntityIceCream(World par1World) 
	{
		super(par1World);
		
		this.texture = "/mods/tutorial/textures/ModelIceCream.png";
		this.moveSpeed = 0.25F;
		
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIBreakDoor(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, this.moveSpeed, false));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        //this.tasks.addTask(4, new EntityAIWander(this, this.moveSpeed));
        
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
	}
 
	public int getMaxHealth() 
	{
		return 20;
	}
	
	public int getAttackStrength(Entity par1Entity)
    {
     return 4;
    }
	
	public String getTexture()
    {
		return "/mods/tutorial/textures/ModelIceCream.png";
    }
	
	public int getTotalArmorValue()
    {
        return 2;
    }
	
	public void onLivingUpdate()
    {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote)
        {
            float var1 = this.getBrightness(1.0F);

            if (var1 > 0.5F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F)
            {
                this.setFire(8);
            }
        }

        super.onLivingUpdate();
    }
	
	protected String getLivingSound()
    {
        return "mob.ghast.moan";
    }

    
    //Returns the sound this mob makes when it is hurt.
    
    protected String getHurtSound()
    {
        return "mob.ghast.scream";
    }

    
    //Returns the sound this mob makes on death.
    
    protected String getDeathSound()
    {
        return "mob.ghast.death";
    }
    
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.worldObj.playSoundAtEntity(this, "mob.zombie.step", 0.15F, 1.0F);
    }
    
    protected int getDropItemId()
    {
        return Item.ingotGold.itemID;
    }
    
    protected void dropRareDrop(int par1)
    {
        switch (this.rand.nextInt(2))
        {
            case 0:
                this.dropItem(Item.ingotIron.itemID, 1);
                break;
            case 1:
                this.dropItem(Item.helmetSteel.itemID, 1);
                break;
        }
    }
    
    protected void dropFewItems(boolean par1, int par2)
    {
    	if(this.rand.nextInt(3) == 0)
    	{
    		this.dropItem(Item.appleRed.itemID, 1);
    	}
    }
}*/


public class EntityIceCream extends EntityFlying implements IMob
{
   
	public int courseChangeCooldown = 0;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    public double tempX = 0.0D;
    public double tempY = 0.0D;
    public double tempZ = 0.0D;
    //public double maxMove = 0.0D;
    private Entity targetedEntity = null;
 
    //Cooldown time between target loss and new target aquirement.
    private int aggroCooldown = 0;
    public int prevAttackCounter = 0;
    public int attackCounter = 0;

    // The explosion radius of spawned fireballs.
    private int explosionStrength = 1;

    public EntityIceCream(World par1World)
    {
        super(par1World);
        this.texture = "/mods/tutorial/textures/ModelIceCream.png";
        //this.setSize(4.0F, 4.0F);
        //this.isImmuneToFire = true;
        this.experienceValue = 5;
        
        this.setHomeArea((int)this.posX, (int)this.posY, (int)this.posZ, 200);
        
    }

    
     //Called when the entity is attacked.
     
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else if ("fireball".equals(par1DamageSource.getDamageType()) && par1DamageSource.getEntity() instanceof EntityPlayer)
        {
            super.attackEntityFrom(par1DamageSource, 1000);
            ((EntityPlayer)par1DamageSource.getEntity()).triggerAchievement(AchievementList.ghast);
            return true;
        }
        else
        {
            return super.attackEntityFrom(par1DamageSource, par2);
        }
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }

    public int getMaxHealth()
    {
        return 10;
    }

    
    //Called to update the entity's position/logic.
     
    public void onUpdate()
    {
        super.onUpdate();
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);
        this.texture = b0 == 1 ? "/mods/tutorial/textures/ModelIceCream.png" : "/mods/tutorial/textures/ModelIceCream.png";
    }

    protected void updateEntityActionState()
    {
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == 0)
        {
            this.setDead();
        }

        this.despawnEntity();
        this.prevAttackCounter = this.attackCounter;
        double d0 = this.waypointX - this.posX;
        double d1 = this.waypointY - this.posY;
        double d2 = this.waypointZ - this.posZ;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;

        if (d3 < 1.0D || d3 > 3600.0D)
        {
            this.tempX = this.posX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.tempZ = this.posZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.tempY = this.posY + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            
            if(this.tempY >= 100)
            {
            	this.waypointX = this.posX - 2*(this.waypointX - this.posX);
            	this.waypointY = this.posY - 2*(this.waypointY - this.posY);
            	this.waypointZ = this.posZ - 2*(this.waypointZ - this.posZ);
            }
            else
            {
            	this.waypointX = this.tempX;
            	this.waypointY = this.tempY;
            	this.waypointZ = this.tempZ;
            }
        }

        if (this.courseChangeCooldown-- <= 0)
        {
            this.courseChangeCooldown += this.rand.nextInt(5) + 2;
            d3 = (double)MathHelper.sqrt_double(d3);

            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3))
            {
                this.motionX += d0 / d3 * 0.1D;
                this.motionZ += d2 / d3 * 0.1D;
                this.motionY += d1 / d3 * 0.1D;
            }
            else
            {
                this.waypointX = this.posX;
                this.waypointY = this.posY;
                this.waypointZ = this.posZ;
            }
        }

        if (this.targetedEntity != null && this.targetedEntity.isDead)
        {
            this.targetedEntity = null;
        }

        if (this.targetedEntity == null || this.aggroCooldown-- <= 0)
        {
            this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);

            if (this.targetedEntity != null)
            {
                this.aggroCooldown = 20;
            }
        }

        double d4 = 64.0D;

        if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity(this) < d4 * d4)
        {
            double d5 = this.targetedEntity.posX - this.posX;
            double d6 = this.targetedEntity.boundingBox.minY + (double)(this.targetedEntity.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
            double d7 = this.targetedEntity.posZ - this.posZ;
            this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(d5, d7)) * 180.0F / (float)Math.PI;

            if (this.canEntityBeSeen(this.targetedEntity))
            {
                if (this.attackCounter == 10)
                {
                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1007, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                }

                ++this.attackCounter;

                if (this.attackCounter == 20)
                {
                    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1008, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
                    EntityLargeFireball entitylargefireball = new EntityLargeFireball(this.worldObj, this, d5, d6, d7);
                    entitylargefireball.field_92057_e = this.explosionStrength;
                    double d8 = 4.0D;
                    Vec3 vec3 = this.getLook(1.0F);
                    entitylargefireball.posX = this.posX + vec3.xCoord * d8;
                    entitylargefireball.posY = this.posY + (double)(this.height / 2.0F) + 0.5D;
                    entitylargefireball.posZ = this.posZ + vec3.zCoord * d8;
                    this.worldObj.spawnEntityInWorld(entitylargefireball);
                    this.attackCounter = -10;
                }
            }
            else if (this.attackCounter > 0)
            {
                --this.attackCounter;
            }
        }
        else
        {
            this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;

            if (this.attackCounter > 0)
            {
                --this.attackCounter;
            }
        }

        if (!this.worldObj.isRemote)
        {
            byte b0 = this.dataWatcher.getWatchableObjectByte(16);
            byte b1 = (byte)(this.attackCounter > 10 ? 1 : 0);

            if (b0 != b1)
            {
                this.dataWatcher.updateObject(16, Byte.valueOf(b1));
            }
        }
    }

    
    //True if the ghast has an unobstructed line of travel to the waypoint.
    
    private boolean isCourseTraversable(double par1, double par3, double par5, double par7)
    {
        double d4 = (this.waypointX - this.posX) / par7;
        double d5 = (this.waypointY - this.posY) / par7;
        double d6 = (this.waypointZ - this.posZ) / par7;
        AxisAlignedBB axisalignedbb = this.boundingBox.copy();

        for (int i = 1; (double)i < par7; ++i)
        {
            axisalignedbb.offset(d4, d5, d6);

            if (!this.worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    
    //Returns the sound this mob makes while it's alive.
    
    protected String getLivingSound()
    {
        return "mob.ghast.moan";
    }

    
    //Returns the sound this mob makes when it is hurt.
    
    protected String getHurtSound()
    {
        return "mob.ghast.scream";
    }

    
    //Returns the sound this mob makes on death.
    
    protected String getDeathSound()
    {
        return "mob.ghast.death";
    }

    
    //Returns the item ID for the item the mob drops on death.
    
    protected int getDropItemId()
    {
        return 757;
    }

    
    //Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
    //par2 - Level of Looting used to kill this mob.
    
    protected void dropFewItems(boolean par1, int par2)
    {
        int j = this.rand.nextInt(2) + this.rand.nextInt(1 + par2);
        int k;

        for (k = 0; k < j; ++k)
        {
            this.dropItem(757, 1);
        }
    }

    
    //Returns the volume for the sounds this mob makes.
    
    protected float getSoundVolume()
    {
        return 10.0F;
    }

    
    //Checks if the entity's current position is a valid location to spawn this entity.
    
    public boolean getCanSpawnHere()
    {
        return this.rand.nextInt(20) == 0 && super.getCanSpawnHere() && this.worldObj.difficultySetting > 0;
    }

    
    //Will return how many at most can spawn in a chunk at once.
    
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    
    //(abstract) Protected helper method to write subclass entity data to NBT.
    
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("ExplosionPower", this.explosionStrength);
    }

   
    //(abstract) Protected helper method to read subclass entity data from NBT.
    
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.hasKey("ExplosionPower"))
        {
            this.explosionStrength = par1NBTTagCompound.getInteger("ExplosionPower");
        }
    }
}
