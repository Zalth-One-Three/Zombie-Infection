package com.zalthonethree.zombieinfection.event;

import morph.api.Api;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

import com.zalthonethree.zombieinfection.ZombieInfection;
import com.zalthonethree.zombieinfection.api.ZombieInfectionAPI;
import com.zalthonethree.zombieinfection.entity.EntityZombieChicken;
import com.zalthonethree.zombieinfection.entity.EntityZombieCow;
import com.zalthonethree.zombieinfection.entity.EntityZombiePig;
import com.zalthonethree.zombieinfection.entity.EntityZombieSheep;
import com.zalthonethree.zombieinfection.entity.IZombieInfectionMob;
import com.zalthonethree.zombieinfection.handler.ConfigurationHandler;
import com.zalthonethree.zombieinfection.potion.PotionHelper;
import com.zalthonethree.zombieinfection.utility.Utilities;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class InfectionEvent /*extends EntityDragon*/ {
	@SubscribeEvent public void onAttack(LivingHurtEvent event) {
		if (event.source instanceof EntityDamageSource) {
			EntityDamageSource source = (EntityDamageSource) event.source;
			Entity attacker = source.getEntity();
			boolean infectiousMob = false;
			int infectionChance = 0;
			for (int entityId : ZombieInfectionAPI.getCustionInfectiousMobs()) {
				if (EntityList.getEntityID(attacker) == entityId) {
					infectiousMob = true;
					infectionChance = ZombieInfectionAPI.getCustomInfectionChances().get(entityId);
				}
			}
			for (Class<?> clazz : attacker.getClass().getInterfaces()) {
				if (clazz.isInterface()) {
					if (clazz.equals(IZombieInfectionMob.class)) {
						infectiousMob = true;
						infectionChance = ConfigurationHandler.getPlayerInfectionChance();
					}
				}
			}
			if (infectiousMob) {
				Entity target = event.entity;
				if (target instanceof EntityPlayer) {
					EntityPlayer attacked = (EntityPlayer) target;
					if (Api.getMorphEntity(attacked.getCommandSenderName(), false) != null) {
						if (Api.getMorphEntity(attacked.getCommandSenderName(), false) instanceof EntityZombie) infectionChance = (int) infectionChance / 2;
					}
					if ((attacked.getRNG().nextInt(100) + 1) <= infectionChance) {
						if (!attacked.isPotionActive(ZombieInfection.potionInfection)) {
							attacked.addChatMessage(new ChatComponentTranslation("zombieinfection.chat.infected"));
							attacked.addPotionEffect(PotionHelper.createInfection(0));
						}
					}
				}
			} else if (attacker instanceof EntityPlayer) {
				Entity target = event.entity;
				if (target instanceof EntityPlayer) {
					EntityPlayer attacked = (EntityPlayer) target;
					EntityPlayer possiblespreader = (EntityPlayer) attacker;
					if (possiblespreader.isPotionActive(ZombieInfection.potionInfection)
					&& !attacked.isPotionActive(ZombieInfection.potionInfection)) {
						attacked.addChatMessage(new ChatComponentTranslation("zombieinfection.chat.playerinfected"));
						attacked.addPotionEffect(PotionHelper.createInfection(0));
					}
				} else if (target instanceof EntityVillager) {
					EntityVillager attacked = (EntityVillager) target;
					EntityPlayer possiblespreader = (EntityPlayer) attacker;
					if (possiblespreader.isPotionActive(ZombieInfection.potionInfection)) {
						if (attacked.getRNG().nextInt(100) + 1 <= ConfigurationHandler.getVillagerInfectionChance()) {
							if (!attacked.isPotionActive(Potion.wither)) {
								attacked.addPotionEffect(PotionHelper.createWither(0));
							}
						}
					}
				} else if (target instanceof EntityChicken
				|| target instanceof EntityCow
				|| target instanceof EntityPig
				|| target instanceof EntitySheep) {
					EntityCreature attacked = (EntityCreature) target;
					EntityPlayer possiblespreader = (EntityPlayer) attacker;
					if (possiblespreader.isPotionActive(ZombieInfection.potionInfection)) {
						if (attacked.getRNG().nextInt(100) + 1 <= ConfigurationHandler.getAnimalInfectionChance()) {
							if (!attacked.isPotionActive(Potion.wither)) {
								attacked.addPotionEffect(PotionHelper.createWither(0));
							}
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent public void onDeath(LivingDeathEvent event) {
		if (event.source instanceof EntityDamageSource) {
			EntityDamageSource source = (EntityDamageSource) event.source;
			Entity attacker = source.getEntity();
			if (attacker instanceof EntityPlayer) {
				Entity target = event.entity;
				if (target instanceof EntityVillager) {
					EntityVillager attacked = (EntityVillager) target;
					EntityPlayer possiblespreader = (EntityPlayer) attacker;
					if (possiblespreader.isPotionActive(ZombieInfection.potionInfection)) {
						if (attacked.isPotionActive(Potion.wither)) {
							if (attacked.getRNG().nextInt(100) + 1 <= ConfigurationHandler.getVillagerInfectionChance()) {
								EntityZombie entityzombie = new EntityZombie(attacked.worldObj);
								entityzombie.copyLocationAndAnglesFrom(attacked);
								attacked.worldObj.removeEntity(attacked);
								entityzombie.onSpawnWithEgg((IEntityLivingData) null);
								entityzombie.setVillager(true);
								
								if (attacked.isChild()) {
									entityzombie.setChild(true);
								}
								
								attacked.worldObj.spawnEntityInWorld(entityzombie);
								attacked.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, (int) attacked.posX, (int) attacked.posY, (int) attacked.posZ, 0);
							}
						}
					}
				} else if (target instanceof EntityChicken
				|| target instanceof EntityCow
				|| target instanceof EntityPig
				|| target instanceof EntitySheep) {
					EntityCreature attacked = (EntityCreature) target;
					EntityPlayer possiblespreader = (EntityPlayer) attacker;
					if (possiblespreader.isPotionActive(ZombieInfection.potionInfection)) {
						if (attacked.isPotionActive(Potion.wither) && !attacked.isChild()) {
							if (attacked.getRNG().nextInt(100) + 1 <= ConfigurationHandler.getAnimalInfectionChance()) { //TODO getZombificationChanceFor(Entity)
								EntityCreature entityzombified = zombifyEntity(target);
								entityzombified.copyLocationAndAnglesFrom(attacked);
								attacked.worldObj.removeEntity(attacked);
								entityzombified.onSpawnWithEgg((IEntityLivingData) null);
								
								attacked.worldObj.spawnEntityInWorld(entityzombified);
								attacked.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, (int) attacked.posX, (int) attacked.posY, (int) attacked.posZ, 0);
							}
						}
					}
				}
			}
		}
	}
	
	private static EntityCreature zombifyEntity(Entity original) {
		if (original instanceof EntityChicken) return new EntityZombieChicken(original.worldObj);
		if (original instanceof EntityCow) return new EntityZombieCow(original.worldObj);
		if (original instanceof EntityPig) return new EntityZombiePig(original.worldObj);
		if (original instanceof EntitySheep) return new EntityZombieSheep(original.worldObj);
		return new EntityZombie(original.worldObj);
	}
	
	@SubscribeEvent public void onEaten(PlayerUseItemEvent.Finish event) {
		if (Utilities.isServerSide()) {
			EntityPlayer player = event.entityPlayer;
			ItemStack stack = event.item;
			int infectionChance = 0;
			for (String itemName : ZombieInfectionAPI.getCustomFoodInfections().keySet()) {
				if (stack.getUnlocalizedName().equalsIgnoreCase(itemName)) {
					infectionChance = ZombieInfectionAPI.getCustomFoodInfections().get(itemName);
					if (player.getRNG().nextInt(100) + 1 <= infectionChance) {
						if (!player.isPotionActive(ZombieInfection.potionInfection)) {
							player.addChatMessage(new ChatComponentTranslation("zombieinfection.chat.rotteninfection"));
							player.addPotionEffect(PotionHelper.createInfection(0));
						}
					}
					break;
				}
			}
		}
	}
}