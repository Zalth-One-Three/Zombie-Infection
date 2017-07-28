package com.zalthonethree.zombieinfection.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
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
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.zalthonethree.zombieinfection.api.IZombieInfectionMob;
import com.zalthonethree.zombieinfection.init.ModRegistry;

public class EntityZombieChicken extends EntityMob implements IZombieInfectionMob {
	public float wingRotation;
	public float destPos;
	public float lastDestPos;
	public float lastWingRotation;
	public float wingRotDelta = 1.0F;
	
	public int timeUntilNextEgg;
	// TODO Possible zombieChickenZombie, ZombieChicken with baby zombie on back
	
	public EntityZombieChicken(World world) {
		super(world);
		this.setSize(0.3F, 0.7F); // If we were copying the chicken size this is wrong, it should be 0.4, 0.7.
		this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
		this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityChicken>(this, EntityChicken.class, false));
	}
	
	@Override public float getEyeHeight() {
		return this.height;
	}
	
	@Override protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
	}
	
	@Override public boolean attackEntityAsMob(Entity entity) {
		boolean flag = super.attackEntityAsMob(entity);
		
		if (flag) {
			int i = this.world.getDifficulty().getDifficultyId();
			
			if (this.getHeldItemMainhand() == null && this.isBurning() && this.rand.nextFloat() < (float) i * 0.3F) {
				entity.setFire(2 * i);
			}
		}
		
		return flag;
	}
	
	@Override protected SoundEvent getAmbientSound() { return SoundEvents.ENTITY_CHICKEN_AMBIENT; }
	@Override protected SoundEvent getHurtSound(DamageSource source) { return SoundEvents.ENTITY_CHICKEN_HURT; }
	@Override protected SoundEvent getDeathSound() { return SoundEvents.ENTITY_CHICKEN_DEATH; }
	

	@Override protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
	}
	
	@Override protected float getSoundVolume() { return 0.4F; }
	@Override public EnumCreatureAttribute getCreatureAttribute() { return EnumCreatureAttribute.UNDEAD; }
	@Override protected Item getDropItem() { return Items.ROTTEN_FLESH; }
	
	@Override public void onLivingUpdate() {
		if (this.world.isDaytime() && !this.world.isRemote && !this.isChild()) {
			float f = this.getBrightness();
			
			if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canBlockSeeSky(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY), MathHelper.floor(this.posZ)))) {
				this.setFire(8);
			}
		}
		
		super.onLivingUpdate();
		
		this.lastWingRotation = this.wingRotation;
		this.lastDestPos = this.destPos;
		this.destPos = (float) ((double) this.destPos + (double) (this.onGround ? -1 : 4) * 0.3D);
		
		if (this.destPos < 0.0F) {
			this.destPos = 0.0F;
		}
		
		if (this.destPos > 1.0F) {
			this.destPos = 1.0F;
		}
		
		if (!this.onGround && this.wingRotDelta < 1.0F) {
			this.wingRotDelta = 1.0F;
		}
		
		this.wingRotDelta = (float) ((double) this.wingRotDelta * 0.9D);
		
		if (!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.6D;
		}
		
		this.wingRotation += this.wingRotDelta * 2.0F;
		
		if (!this.world.isRemote && !this.isChild() && this.timeUntilNextEgg-- <= 0) {
			this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			this.dropItem(ModRegistry.INFECTED_EGG, 1);
			this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
		}
	}
	
	@Override public void onKillEntity(EntityLivingBase entityLivingIn) {
		super.onKillEntity(entityLivingIn);
		
		if ((this.world.getDifficulty() == EnumDifficulty.NORMAL || this.world.getDifficulty() == EnumDifficulty.HARD && !entityLivingIn.isChild()) && entityLivingIn instanceof EntityChicken) {
			if (this.world.getDifficulty() != EnumDifficulty.HARD && this.rand.nextBoolean()) { return; }
			
			EntityZombieChicken entityzombiechicken = new EntityZombieChicken(this.world);
			entityzombiechicken.copyLocationAndAnglesFrom(entityLivingIn);
			this.world.removeEntity(entityLivingIn);
			entityzombiechicken.onInitialSpawn(this.world.getDifficultyForLocation(this.getPosition()), (IEntityLivingData) null);
			
			this.world.spawnEntity(entityzombiechicken);
		}
	}

	@Override public void writeEntityToNBT(NBTTagCompound tagCompound) {
		super.writeEntityToNBT(tagCompound);
		tagCompound.setInteger("EggLayTime", this.timeUntilNextEgg);
	}
	
	@Override public void readEntityFromNBT(NBTTagCompound tagCompound) {
		super.readEntityFromNBT(tagCompound);
		if (tagCompound.hasKey("EggLayTime")) {
			this.timeUntilNextEgg = tagCompound.getInteger("EggLayTime");
		}
	}
	
	@Override public int getPlayerInfectionChance() { return 10; }
}