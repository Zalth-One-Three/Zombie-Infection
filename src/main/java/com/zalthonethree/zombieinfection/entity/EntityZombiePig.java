package com.zalthonethree.zombieinfection.entity;

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
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityZombiePig extends EntityMob/*, EntityDragon*/ implements IZombieInfectionMob{
	public EntityZombiePig(World world) {
		super(world);
		this.setSize(0.9F, 0.9F);
		this.getNavigator().setAvoidsWater(false);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPig.class, 1.0D, true));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPig.class, 0, false));
	}
	
	@Override protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
	}
	
	@Override public boolean isAIEnabled() { return true; }
	
	@Override public boolean attackEntityAsMob(Entity entity) {
		boolean flag = super.attackEntityAsMob(entity);
		
		if (flag) {
			int i = this.worldObj.difficultySetting.getDifficultyId();
			
			if (this.getHeldItem() == null && this.isBurning() && this.rand.nextFloat() < (float) i * 0.3F) {
				entity.setFire(2 * i);
			}
		}
		
		return flag;
	}
	
	@Override protected String getLivingSound() { return "mob.pig.say"; }
	@Override protected String getHurtSound() { return "mob.pig.say"; }
	@Override protected String getDeathSound() { return "mob.pig.death"; }
	
	@Override protected void playStepSound(int x, int y, int z, Block blockIn) {
		this.playSound("mob.pig.step", 0.15F, 1.0F);
	}
	
	@Override protected float getSoundVolume() { return 0.4F; }
	@Override public EnumCreatureAttribute getCreatureAttribute() { return EnumCreatureAttribute.UNDEAD; }
	@Override protected Item getDropItem() { return Items.rotten_flesh; }
	
	@Override public void onLivingUpdate() {
		if (this.worldObj.isDaytime() && !this.worldObj.isRemote && !this.isChild()) {
			float f = this.getBrightness(1.0F);
			
			if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))) {
				this.setFire(8);
			}
		}
		
		super.onLivingUpdate();
	}
	
	@Override public void onKillEntity(EntityLivingBase entityLivingIn) {
		super.onKillEntity(entityLivingIn);
		
		if ((this.worldObj.difficultySetting == EnumDifficulty.NORMAL || this.worldObj.difficultySetting == EnumDifficulty.HARD && !entityLivingIn.isChild()) && entityLivingIn instanceof EntityPig) {
			if (this.worldObj.difficultySetting != EnumDifficulty.HARD && this.rand.nextBoolean()) { return; }
			
			EntityZombiePig entityzombiepig = new EntityZombiePig(this.worldObj);
			entityzombiepig.copyLocationAndAnglesFrom(entityLivingIn);
			this.worldObj.removeEntity(entityLivingIn);
			entityzombiepig.onSpawnWithEgg((IEntityLivingData) null);
			
			this.worldObj.spawnEntityInWorld(entityzombiepig);
			this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
		}
	}
	
	@Override public int getPlayerInfectionChance() { return 10; }
}