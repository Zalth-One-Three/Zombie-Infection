package com.zalthonethree.zombieinfection.event;

import com.zalthonethree.zombieinfection.api.IZombieInfectionMob;
import com.zalthonethree.zombieinfection.api.ZombieInfectionAPI;
import com.zalthonethree.zombieinfection.entity.EntityZombieChicken;
import com.zalthonethree.zombieinfection.entity.EntityZombieCow;
import com.zalthonethree.zombieinfection.entity.EntityZombiePig;
import com.zalthonethree.zombieinfection.entity.EntityZombieSheep;
import com.zalthonethree.zombieinfection.handler.ConfigurationHandler;
import com.zalthonethree.zombieinfection.init.ModRegistry;
import com.zalthonethree.zombieinfection.potion.PotionHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InfectionEvent {
	@SubscribeEvent public void onAttack(LivingHurtEvent event) {
		if (event.getSource() instanceof EntityDamageSource) {
			EntityDamageSource source = (EntityDamageSource) event.getSource();
			Entity attacker = source.getTrueSource();
			if (attacker == null) return;
			boolean infectiousMob = false;
			int infectionChance = 0;
			for (int entityId : ZombieInfectionAPI.getCustomInfectiousMobs()) {
				if (attacker.getEntityId() == entityId) {
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
				Entity target = event.getEntity();
				if (target instanceof EntityPlayer) {
					EntityPlayer attacked = (EntityPlayer) target;
					if ((attacked.getRNG().nextInt(100) + 1) <= infectionChance) {
						if (!attacked.isPotionActive(ModRegistry.POTION_INFECTION)) {
							attacked.sendMessage(new TextComponentTranslation("zombieinfection.chat.infected"));
							attacked.addPotionEffect(PotionHelper.createInfection(0));
						}
					}
				}
			} else if (attacker instanceof EntityPlayer) {
				Entity target = event.getEntity();
				if (target instanceof EntityPlayer) {
					EntityPlayer attacked = (EntityPlayer) target;
					EntityPlayer possiblespreader = (EntityPlayer) attacker;
					if (possiblespreader.isPotionActive(ModRegistry.POTION_INFECTION)
					&& !attacked.isPotionActive(ModRegistry.POTION_INFECTION)) {
						attacked.sendMessage(new TextComponentTranslation("zombieinfection.chat.playerinfected"));
						attacked.addPotionEffect(PotionHelper.createInfection(0));
					}
				} else if (target instanceof EntityVillager) {
					EntityVillager attacked = (EntityVillager) target;
					EntityPlayer possiblespreader = (EntityPlayer) attacker;
					if (possiblespreader.isPotionActive(ModRegistry.POTION_INFECTION)) {
						if (attacked.getRNG().nextInt(100) + 1 <= ConfigurationHandler.getVillagerInfectionChance()) {
							if (!attacked.isPotionActive(Potion.getPotionFromResourceLocation("wither"))) {
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
					if (possiblespreader.isPotionActive(ModRegistry.POTION_INFECTION)) {
						if (attacked.getRNG().nextInt(100) + 1 <= ConfigurationHandler.getAnimalInfectionChance()) {
							if (!attacked.isPotionActive(Potion.getPotionFromResourceLocation("wither"))) {
								attacked.addPotionEffect(PotionHelper.createWither(0));
							}
						}
					}
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@SubscribeEvent public void onDeath(LivingDeathEvent event) {
		if (event.getSource() instanceof EntityDamageSource) {
			EntityDamageSource source = (EntityDamageSource) event.getSource();
			Entity attacker = source.getTrueSource();
			if (attacker instanceof EntityPlayer) {
				Entity target = event.getEntity();
				if (target instanceof EntityVillager) {
					EntityVillager attacked = (EntityVillager) target;
					EntityPlayer possiblespreader = (EntityPlayer) attacker;
					if (possiblespreader.isPotionActive(ModRegistry.POTION_INFECTION)) {
						if (attacked.isPotionActive(Potion.getPotionFromResourceLocation("wither"))) {
							if (attacked.getRNG().nextInt(100) + 1 <= ConfigurationHandler.getVillagerInfectionChance()) {
								EntityZombieVillager entityzombievillager = new EntityZombieVillager(attacked.world);
								entityzombievillager.copyLocationAndAnglesFrom(attacked);
								attacked.world.removeEntity(attacked);
								entityzombievillager.onInitialSpawn(attacked.world.getDifficultyForLocation(new BlockPos(entityzombievillager)), (IEntityLivingData) null);
								entityzombievillager.setProfession(attacked.getProfession());
								
								if (attacked.isChild()) {
									entityzombievillager.setChild(true);
								}
								
								attacked.world.spawnEntity(entityzombievillager);
							}
						}
					}
				} else if (target instanceof EntityChicken
				|| target instanceof EntityCow
				|| target instanceof EntityPig
				|| target instanceof EntitySheep) {
					EntityCreature attacked = (EntityCreature) target;
					EntityPlayer possiblespreader = (EntityPlayer) attacker;
					if (possiblespreader.isPotionActive(ModRegistry.POTION_INFECTION)) {
						if (attacked.isPotionActive(Potion.getPotionFromResourceLocation("wither")) && !attacked.isChild()) {
							if (attacked.getRNG().nextInt(100) + 1 <= ConfigurationHandler.getAnimalInfectionChance()) {
								EntityCreature entityzombified = zombifyEntity(target);
								entityzombified.copyLocationAndAnglesFrom(attacked);
								attacked.world.removeEntity(attacked);
								entityzombified.onInitialSpawn(attacked.world.getDifficultyForLocation(attacked.getPosition()), (IEntityLivingData) null);
								
								attacked.world.spawnEntity(entityzombified);
							}
						}
					}
				}
			}
		}
	}
	
	private static EntityCreature zombifyEntity(Entity original) {
		if (original instanceof EntityChicken) return new EntityZombieChicken(original.world);
		if (original instanceof EntityCow) return new EntityZombieCow(original.world);
		if (original instanceof EntityPig) return new EntityZombiePig(original.world);
		if (original instanceof EntitySheep) return new EntityZombieSheep(original.world);
		return new EntityZombie(original.world);
	}
	
/*	@SubscribeEvent public void onEaten(PlayerUseItemEvent.Finish event) {
		if (Utilities.isServerSide()) {
			EntityPlayer player = event.entityPlayer;
			ItemStack stack = event.item;
			int infectionChance = 0;
			for (String itemName : ZombieInfectionAPI.getCustomFoodInfections().keySet()) {
				if (stack.getUnlocalizedName().equalsIgnoreCase(itemName)) {
					infectionChance = ZombieInfectionAPI.getCustomFoodInfections().get(itemName);
					if (player.getRNG().nextInt(100) + 1 <= infectionChance) {
						if (!player.isPotionActive(ModPotion.potionInfection)) {
							player.addChatMessage(new TextComponentTranslation("zombieinfection.chat.rotteninfection"));
							player.addPotionEffect(PotionHelper.createInfection(0));
						}
					}
					break;
				}
			}
		}
	}*/ // TODO Reimplement
}