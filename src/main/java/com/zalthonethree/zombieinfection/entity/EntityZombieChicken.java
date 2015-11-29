package com.zalthonethree.zombieinfection.entity;

import com.zalthonethree.zombieinfection.api.IZombieInfectionMob;
import com.zalthonethree.zombieinfection.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityZombieChicken extends EntityMob implements IZombieInfectionMob {
	public float field_70886_e;
	public float destPos;
	public float field_70884_g;
	public float field_70888_h;
	public float field_70889_i = 1.0F;
	
	public int timeUntilNextEgg;
	
	public EntityZombieChicken(World world) {
		super(world);
		this.setSize(0.3F, 0.7F);
		this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityChicken.class, 1.0D, true));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityChicken>(this, EntityChicken.class, false));
	}
	
	@Override protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
	}
	
	@Override public boolean attackEntityAsMob(Entity entity) {
		boolean flag = super.attackEntityAsMob(entity);
		
		if (flag) {
			int i = this.worldObj.getDifficulty().getDifficultyId();
			
			if (this.getHeldItem() == null && this.isBurning() && this.rand.nextFloat() < (float) i * 0.3F) {
				entity.setFire(2 * i);
			}
		}
		
		return flag;
	}
	
	@Override protected String getLivingSound() { return "mob.chicken.say"; }
	@Override protected String getHurtSound() { return "mob.chicken.hurt"; }
	@Override protected String getDeathSound() { return "mob.chicken.hurt"; }
	

	@Override protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound("mob.chicken.step", 0.15F, 1.0F);
	}
	
	@Override protected float getSoundVolume() { return 0.4F; }
	@Override public EnumCreatureAttribute getCreatureAttribute() { return EnumCreatureAttribute.UNDEAD; }
	@Override protected Item getDropItem() { return Items.rotten_flesh; }
	
	@Override public void onLivingUpdate() {
		if (this.worldObj.isDaytime() && !this.worldObj.isRemote && !this.isChild()) {
			float f = this.getBrightness(1.0F);
			
			if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.worldObj.canBlockSeeSky(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))) {
				this.setFire(8);
			}
		}
		
		super.onLivingUpdate();
		
		this.field_70888_h = this.field_70886_e;
		this.field_70884_g = this.destPos;
		this.destPos = (float) ((double) this.destPos + (double) (this.onGround ? -1 : 4) * 0.3D);
		
		if (this.destPos < 0.0F) {
			this.destPos = 0.0F;
		}
		
		if (this.destPos > 1.0F) {
			this.destPos = 1.0F;
		}
		
		if (!this.onGround && this.field_70889_i < 1.0F) {
			this.field_70889_i = 1.0F;
		}
		
		this.field_70889_i = (float) ((double) this.field_70889_i * 0.9D);
		
		if (!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.6D;
		}
		
		this.field_70886_e += this.field_70889_i * 2.0F;
		
		if (!this.worldObj.isRemote && !this.isChild() && this.timeUntilNextEgg-- <= 0) {
			this.playSound("mob.chicken.plop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			this.dropItem(ModItems.infectedEgg, 1);
			this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
		}
	}
	
	@Override public void onKillEntity(EntityLivingBase entityLivingIn) {
		super.onKillEntity(entityLivingIn);
		
		if ((this.worldObj.getDifficulty() == EnumDifficulty.NORMAL || this.worldObj.getDifficulty() == EnumDifficulty.HARD && !entityLivingIn.isChild()) && entityLivingIn instanceof EntityChicken) {
			if (this.worldObj.getDifficulty() != EnumDifficulty.HARD && this.rand.nextBoolean()) { return; }
			
			EntityZombieChicken entityzombiechicken = new EntityZombieChicken(this.worldObj);
			entityzombiechicken.copyLocationAndAnglesFrom(entityLivingIn);
			this.worldObj.removeEntity(entityLivingIn);
			entityzombiechicken.onInitialSpawn(this.worldObj.getDifficultyForLocation(this.getPosition()), (IEntityLivingData) null);
			
			this.worldObj.spawnEntityInWorld(entityzombiechicken);
			this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, new BlockPos((int) this.posX, (int) this.posY, (int) this.posZ), 0);
		}
	}

	@Override public int getPlayerInfectionChance() { return 10; }
}