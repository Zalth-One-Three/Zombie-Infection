package com.zalthonethree.zombieinfection.entity.zend;

import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.zalthonethree.zombieinfection.api.IZombieInfectionMob;

public class EntityEnderZombie extends EntityMob implements IZombieInfectionMob {
	private boolean isAggressive;
	
	public EntityEnderZombie(World worldIn) {
		super(worldIn);
		this.setSize(0.6F, 2.9F);
		this.stepHeight = 1.0F;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0D, false));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityZombieDragon.class, 8.0F));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityEnderZombie.AIFindPlayer());
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<EntityZombieDragon>(this, EntityZombieDragon.class, 100, false, false, new com.google.common.base.Predicate<EntityZombieDragon>() {
			public boolean isApplicable(EntityZombieDragon entity) {
				return entity.isEgg();
			}
			
			@Override public boolean apply(EntityZombieDragon entity) {
				return this.isApplicable(entity);
			}
		}));
	}
	
	@Override protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64.0D);
	}
	
	@Override protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, new Short((short) 0));
		this.dataWatcher.addObject(17, new Byte((byte) 0));
		this.dataWatcher.addObject(18, new Byte((byte) 0));
	}
	
	@Override public int getPlayerInfectionChance() {
		return 0;
	}
	
	@Override public float getEyeHeight() {
		return 2.55F;
	}
	
	@Override public void onLivingUpdate() {
		if (this.worldObj.isRemote) {
			for (int i = 0; i < 2; i ++) {
				this.worldObj.spawnParticle(EnumParticleTypes.PORTAL, this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width, this.posY + this.rand.nextDouble() * (double) this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D, new int[0]);
			}
		}
		
		this.isJumping = false;
		super.onLivingUpdate();
	}
	
	@Override protected void updateAITasks() {
		if (this.isWet()) this.attackEntityFrom(DamageSource.drown, 1.0F);
		if (this.isScreaming() && !this.isAggressive && this.rand.nextInt(200) == 0) this.setScreaming(false);
		
		super.updateAITasks();
	}
	
	@Override protected String getLivingSound() {
		return this.isScreaming() ? "mob.endermen.scream" : "mob.endermen.idle";
	}
	
	@Override protected String getHurtSound() {
		return "mob.endermen.hit";
	}
	
	@Override protected String getDeathSound() {
		return "mob.endermen.death";
	}
	
	@Override protected Item getDropItem() {
		return Items.ender_pearl/*ModItems.zombiePearl*/; //TODO Change this
	}
	
	@Override public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isEntityInvulnerable(source)) return false;
		
		if (source.getEntity() == null) {
			if (!this.worldObj.isRemote) {
				this.setScreaming(true);
			}
			
			if (source instanceof EntityDamageSource && source.getEntity() instanceof EntityPlayer) {
				if (source.getEntity() instanceof EntityPlayerMP && ((EntityPlayerMP) source.getEntity()).theItemInWorldManager.isCreative()) {
					this.setScreaming(false);
				} else {
					this.isAggressive = true;
				}
			}
			
			if (source instanceof EntityDamageSourceIndirect) {
				this.isAggressive = false;
				return false;
			}
		}
		
		boolean flag = super.attackEntityFrom(source, amount);
		return flag;
	}
	
	public boolean isScreaming() {
		return this.dataWatcher.getWatchableObjectByte(18) > 0;
	}
	
	public void setScreaming(boolean screaming) {
		this.dataWatcher.updateObject(18, Byte.valueOf((byte) (screaming ? 1 : 0)));
	}
	
	private boolean shouldAttackPlayer(EntityPlayer player) {
		Vec3 vec3 = player.getLook(1.0F).normalize();
		Vec3 vec31 = new Vec3(this.posX - player.posX, this.getEntityBoundingBox().minY + (double) (this.height / 2.0F) - (player.posY + (double) player.getEyeHeight()), this.posZ - player.posZ);
		double d0 = vec31.lengthVector();
		vec31 = vec31.normalize();
		double d1 = vec3.dotProduct(vec31);
		return d1 > 1.0D - 0.025D / d0 ? player.canEntityBeSeen(this) : false;
	}
	
	protected boolean teleportRandomly() {
		double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
		double d1 = this.posY + (double) (this.rand.nextInt(64) - 32);
		double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;
		return this.teleportTo(d0, d1, d2);
	}
	
	protected boolean teleportToEntity(Entity p_70816_1_) {
		Vec3 vec3 = new Vec3(this.posX - p_70816_1_.posX, this.getEntityBoundingBox().minY + (double) (this.height / 2.0F) - p_70816_1_.posY + (double) p_70816_1_.getEyeHeight(), this.posZ - p_70816_1_.posZ);
		vec3 = vec3.normalize();
		double d0 = 16.0D;
		double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.xCoord * d0;
		double d2 = this.posY + (double) (this.rand.nextInt(16) - 8) - vec3.yCoord * d0;
		double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3.zCoord * d0;
		return this.teleportTo(d1, d2, d3);
	}
	
	protected boolean teleportTo(double x, double y, double z) {
		double d3 = this.posX;
		double d4 = this.posY;
		double d5 = this.posZ;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		boolean flag = false;
		BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
		
		if (this.worldObj.isBlockLoaded(blockpos)) {
			boolean flag1 = false;
			
			while (!flag1 && blockpos.getY() > 0) {
				BlockPos blockpos1 = blockpos.down();
				Block block = this.worldObj.getBlockState(blockpos1).getBlock();
				
				if (block.getMaterial().blocksMovement()) {
					flag1 = true;
				} else {
					-- this.posY;
					blockpos = blockpos1;
				}
			}
			
			if (flag1) {
				super.setPositionAndUpdate(this.posX, this.posY, this.posZ);
				
				if (this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(this.getEntityBoundingBox())) {
					flag = true;
				}
			}
		}
		
		if (!flag) {
			this.setPosition(d3, d4, d5);
			return false;
		} else {
			short short1 = 128;
			
			for (int i = 0; i < short1; ++ i) {
				double d9 = (double) i / ((double) short1 - 1.0D);
				float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
				double d6 = d3 + (this.posX - d3) * d9 + (this.rand.nextDouble() - 0.5D) * (double) this.width * 2.0D;
				double d7 = d4 + (this.posY - d4) * d9 + this.rand.nextDouble() * (double) this.height;
				double d8 = d5 + (this.posZ - d5) * d9 + (this.rand.nextDouble() - 0.5D) * (double) this.width * 2.0D;
				this.worldObj.spawnParticle(EnumParticleTypes.PORTAL, d6, d7, d8, (double) f, (double) f1, (double) f2, new int[0]);
			}
			
			this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
			this.playSound("mob.endermen.portal", 1.0F, 1.0F);
			return true;
		}
	}
	
	private class AIFindPlayer extends EntityAINearestAttackableTarget<EntityPlayer> {
		private EntityPlayer player;
		private int cooldown;
		private int field_179451_i;
		private EntityEnderZombie enderzombie = EntityEnderZombie.this;
		
		public AIFindPlayer() {
			super(EntityEnderZombie.this, EntityPlayer.class, true);
		}
		
		/** Returns whether the EntityAIBase should begin execution. */
		@SuppressWarnings({"rawtypes", "unchecked"}) public boolean shouldExecute() {
			double d0 = this.getTargetDistance();
			List list = this.taskOwner.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.taskOwner.getEntityBoundingBox().expand(d0, 4.0D, d0), this.targetEntitySelector);
			Collections.sort(list, this.theNearestAttackableTargetSorter);
			
			if (list.isEmpty()) {
				return false;
			} else {
				this.player = (EntityPlayer) list.get(0);
				return true;
			}
		}
		
		/** Execute a one shot task or start executing a continuous task */
		public void startExecuting() {
			this.cooldown = 5;
			this.field_179451_i = 0;
		}
		
		/** Resets the task */
		public void resetTask() {
			this.player = null;
			this.enderzombie.setScreaming(false);
			super.resetTask();
		}
		
		/** Returns whether an in-progress EntityAIBase should continue executing */
		public boolean continueExecuting() {
			if (this.player != null) {
				if (!this.enderzombie.shouldAttackPlayer(this.player)) {
					return false;
				} else {
					this.enderzombie.isAggressive = true;
					this.enderzombie.faceEntity(this.player, 10.0F, 10.0F);
					return true;
				}
			} else {
				return super.continueExecuting();
			}
		}
		
		/** Updates the task */
		public void updateTask() {
			if (this.player != null) {
				if (-- this.cooldown <= 0) {
					this.targetEntity = this.player;
					this.player = null;
					super.startExecuting();
					this.enderzombie.playSound("mob.endermen.stare", 1.0F, 1.0F);
					this.enderzombie.setScreaming(true);
				}
			} else {
				if (this.targetEntity != null) {
					if (this.targetEntity instanceof EntityPlayer && this.enderzombie.shouldAttackPlayer((EntityPlayer) this.targetEntity)) {
						if (this.targetEntity.getDistanceSqToEntity(this.enderzombie) < 16.0D) {
							this.enderzombie.teleportRandomly();
						}
						
						this.field_179451_i = 0;
					} else if (this.targetEntity.getDistanceSqToEntity(this.enderzombie) > 256.0D && this.field_179451_i ++ >= 30 && this.enderzombie.teleportToEntity(this.targetEntity)) {
						this.field_179451_i = 0;
					}
				}
				
				super.updateTask();
			}
		}
	}
}