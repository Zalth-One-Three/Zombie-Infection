package com.zalthonethree.zombieinfection.event;

import java.util.Iterator;

import morph.api.Api;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import com.zalthonethree.zombieinfection.ZombieInfection;
import com.zalthonethree.zombieinfection.handler.ConfigurationHandler;
import com.zalthonethree.zombieinfection.potion.PotionHelper;
import com.zalthonethree.zombieinfection.utility.FoodTracking;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;

public class InfectedPlayerUpdateEvent /*extends EntityDragon*/ {
	
	@SuppressWarnings("rawtypes") @SubscribeEvent public void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = event.player;
		if (player.isPotionActive(ZombieInfection.potionInfection) && !player.isPotionActive(ZombieInfection.potionCure)) {
			player.addPotionEffect(PotionHelper.createInfection());
			player.addPotionEffect(PotionHelper.createHunger());
			player.addPotionEffect(PotionHelper.createSlowness(1));
			player.addPotionEffect(PotionHelper.createMiningFatigue(1));
			player.addPotionEffect(PotionHelper.createWeakness());
			if (player.getFoodStats().getFoodLevel() > FoodTracking.get(player)) {
				player.getFoodStats().addStats(FoodTracking.get(player) - player.getFoodStats().getFoodLevel(), 0);
			}
			FoodTracking.put(player);
			
			if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
				if (ConfigurationHandler.getMorphEnabled()) Api.forceMorph((EntityPlayerMP) player, (EntityLivingBase) new EntityZombie(player.worldObj));
				if (!FMLCommonHandler.instance().getMinecraftServerInstance().isPVPEnabled() && ConfigurationHandler.getSpreadEnabled()) {
					Iterator players = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList.iterator();
					
					while (players.hasNext()) {
						Object thing = players.next();
						if (thing instanceof EntityPlayer) {
							EntityPlayer anotherPlayer = (EntityPlayer) thing;
							if (anotherPlayer.getDistanceToEntity(player) < ConfigurationHandler.getSpreadDistance()) {
								if (anotherPlayer.getUniqueID() != player.getUniqueID()) {
									anotherPlayer.addPotionEffect(PotionHelper.createInfection());
								}
							}
						}
					}
				}
			}
		} else {
			FoodTracking.remove(player);
		}
	}
}
