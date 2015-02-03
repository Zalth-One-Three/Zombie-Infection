package com.zalthonethree.zombieinfection.event;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;

import com.zalthonethree.zombieinfection.ZombieInfection;
import com.zalthonethree.zombieinfection.api.CustomInfectionEffect;
import com.zalthonethree.zombieinfection.api.ZombieInfectionAPI;
import com.zalthonethree.zombieinfection.handler.ConfigurationHandler;
import com.zalthonethree.zombieinfection.potion.PotionHelper;
import com.zalthonethree.zombieinfection.utility.FoodTracking;
import com.zalthonethree.zombieinfection.utility.TimeInfectedTracking;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;

public class InfectedPlayerUpdateEvent /*extends EntityDragon*/ {
	
	@SuppressWarnings("rawtypes") @SubscribeEvent public void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = event.player;
		if (player.isPotionActive(ZombieInfection.potionInfection) && !player.isPotionActive(ZombieInfection.potionCure)) {
			int timeInfected = TimeInfectedTracking.getTicksInfected(player);
			for (CustomInfectionEffect customEffect : ZombieInfectionAPI.getCustomInfectionEffects()) {
				customEffect.run(player, timeInfected);
			}
			player.addPotionEffect(PotionHelper.createInfection(timeInfected < (20 * 60 * 2) ? 0 : 1));
			player.addPotionEffect(PotionHelper.createHunger(timeInfected < (20 * 60 * 2) ? 0 : 1));
			player.addPotionEffect(PotionHelper.createSlowness(timeInfected < (20 * 60 * 2) ? 0 : 1));
			player.addPotionEffect(PotionHelper.createMiningFatigue(timeInfected < (20 * 60 * 2) ? 0 : 1));
			player.addPotionEffect(PotionHelper.createWeakness(timeInfected < (20 * 60 * 2) ? 0 : 1));
			if (player.getFoodStats().getFoodLevel() > FoodTracking.get(player)) {
				player.getFoodStats().addStats(FoodTracking.get(player) - player.getFoodStats().getFoodLevel(), 0);
			}
			TimeInfectedTracking.update(player);
			FoodTracking.put(player);
			
			if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
				if (!FMLCommonHandler.instance().getMinecraftServerInstance().isPVPEnabled() && ConfigurationHandler.getSpreadEnabled()) {
					Iterator players = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList.iterator();
					
					while (players.hasNext()) {
						Object thing = players.next();
						if (thing instanceof EntityPlayer) {
							EntityPlayer anotherPlayer = (EntityPlayer) thing;
							if (anotherPlayer.getDistanceToEntity(player) < ConfigurationHandler.getSpreadDistance()) {
								if (anotherPlayer.getUniqueID() != player.getUniqueID()) {
									anotherPlayer.addPotionEffect(PotionHelper.createInfection(0));
								}
							}
						}
					}
				}
			}
		} else {
			FoodTracking.remove(player);
			TimeInfectedTracking.remove(player);
		}
	}
}
