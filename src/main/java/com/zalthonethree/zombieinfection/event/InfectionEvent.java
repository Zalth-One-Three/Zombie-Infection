package com.zalthonethree.zombieinfection.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.zalthonethree.zombieinfection.ZombieInfection;
import com.zalthonethree.zombieinfection.api.ZombieInfectionAPI;
import com.zalthonethree.zombieinfection.handler.ConfigurationHandler;
import com.zalthonethree.zombieinfection.potion.PotionHelper;

public class InfectionEvent /*extends EntityDragon*/ {
	@SubscribeEvent public void onAttack(LivingHurtEvent event) {
		if (event.source instanceof EntityDamageSource) {
			EntityDamageSource source = (EntityDamageSource) event.source;
			Entity attacker = source.getEntity();
			boolean infectiousMob = false;
			int infectionChance = 10;
			if (attacker instanceof EntityZombie) infectiousMob = true;
			for (int entityId : ZombieInfectionAPI.getCustionInfectiousMobs()) {
				if (EntityList.getEntityID(attacker) == entityId) {
					infectiousMob = true;
					infectionChance = ZombieInfectionAPI.getCustomInfectionChances().get(entityId);
				}
			}
			if (infectiousMob) {
				Entity target = event.entity;
				if (target instanceof EntityPlayer) {
					EntityPlayer attacked = (EntityPlayer) target;
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
							} else {
								if (attacked.getHealth() <= 0) {
									EntityZombie entityzombie = new EntityZombie(attacked.worldObj);
									entityzombie.copyLocationAndAnglesFrom(attacked);
									attacked.worldObj.removeEntity(attacked);
									entityzombie.onSpawnFirstTime(attacked.worldObj.getDifficultyForLocation(new BlockPos(entityzombie)), (IEntityLivingData) null);
									entityzombie.setVillager(true);
									
									if (attacked.isChild()) {
										entityzombie.setChild(true);
									}
									
									attacked.worldObj.spawnEntityInWorld(entityzombie);
									attacked.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1016, new BlockPos((int) attacked.posX, (int) attacked.posY, (int) attacked.posZ), 0);
								}
							}
						}
					}
				}
			}
		}
	}
}