package com.zalthonethree.zombieinfection.event;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;

import com.zalthonethree.zombieinfection.ZombieInfection;
import com.zalthonethree.zombieinfection.potion.PotionHelper;
import com.zalthonethree.zombieinfection.utility.FoodTracking;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class InfectedPlayerUpdateEvent /*extends EntityDragon*/ {
	
	@SuppressWarnings("rawtypes") @SubscribeEvent public void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = event.player;
		if (player.isPotionActive(ZombieInfection.potionInfection) && !player.isPotionActive(ZombieInfection.potionCure)) {
			player.addPotionEffect(PotionHelper.createInfection());
			player.addPotionEffect(PotionHelper.createHunger());
			player.addPotionEffect(PotionHelper.createSlowness());
			player.addPotionEffect(PotionHelper.createMiningFatigue());
			player.addPotionEffect(PotionHelper.createWeakness());
			if (player.getFoodStats().getFoodLevel() > FoodTracking.get(player)) {
				player.getFoodStats().addStats(player.getFoodStats().getFoodLevel() - FoodTracking.get(player), 0);
			}
			FoodTracking.put(player);
			
			if (!FMLCommonHandler.instance().getMinecraftServerInstance().isPVPEnabled()) { //TODO: && configOption
				Iterator players = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList.iterator();
				
				while (players.hasNext()) {
					Object thing = players.next();
					if (thing instanceof EntityPlayer) {
						EntityPlayer anotherPlayer = (EntityPlayer) thing;
						if (anotherPlayer.getDistanceToEntity(player) < 3) {
							if (anotherPlayer.getUniqueID() != player.getUniqueID()) {
								anotherPlayer.addPotionEffect(PotionHelper.createInfection());
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
