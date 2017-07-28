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
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.zalthonethree.zombieinfection.api.IZombieInfectionMob;
import com.zalthonethree.zombieinfection.init.ModRegistry;

public class EntityZombieCow extends EntityMob implements IZombieInfectionMob {
	public EntityZombieCow(World world) {
		super(world);
		this.setSize(0.9F, 1.3F);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
		this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityCow>(this, EntityCow.class, false));
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
	
	@Override protected SoundEvent getAmbientSound() { return SoundEvents.ENTITY_COW_AMBIENT; }
	@Override protected SoundEvent getHurtSound(DamageSource source) { return SoundEvents.ENTITY_COW_HURT; }
	@Override protected SoundEvent getDeathSound() { return SoundEvents.ENTITY_COW_DEATH; }
	
	@Override protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
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
	}
	
	@Override public void onKillEntity(EntityLivingBase entityLivingIn) {
		super.onKillEntity(entityLivingIn);
		
		if ((this.world.getDifficulty() == EnumDifficulty.NORMAL || this.world.getDifficulty() == EnumDifficulty.HARD && !entityLivingIn.isChild()) && entityLivingIn instanceof EntityCow) {
			if (this.world.getDifficulty() != EnumDifficulty.HARD && this.rand.nextBoolean()) { return; }
			
			EntityZombieCow entityzombiecow = new EntityZombieCow(this.world);
			entityzombiecow.copyLocationAndAnglesFrom(entityLivingIn);
			this.world.removeEntity(entityLivingIn);
			entityzombiecow.onInitialSpawn(this.world.getDifficultyForLocation(this.getPosition()), (IEntityLivingData) null);
			
			this.world.spawnEntity(entityzombiecow);
		}
	}
	
	@Override public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!stack.isEmpty() && stack.getItem() == Items.BUCKET) {
			if ((stack.getCount() - 1) == 1) {
				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModRegistry.INFECTED_MILK));
			} else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.MILK_BUCKET))) {
				player.dropItem(new ItemStack(Items.MILK_BUCKET, 1, 0), false);
			}
			
			return true;
		} else {
			return super.processInteract(player, hand);
		}
	}
	
	@Override public int getPlayerInfectionChance() { return 10; }
}