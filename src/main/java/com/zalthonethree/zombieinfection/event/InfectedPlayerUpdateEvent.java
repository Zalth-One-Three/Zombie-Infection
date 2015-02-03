package com.zalthonethree.zombieinfection.event;

import java.util.Calendar;
import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import com.zalthonethree.zombieinfection.ZombieInfection;
import com.zalthonethree.zombieinfection.api.CustomInfectionEffect;
import com.zalthonethree.zombieinfection.api.ZombieInfectionAPI;
import com.zalthonethree.zombieinfection.handler.ConfigurationHandler;
import com.zalthonethree.zombieinfection.potion.PotionHelper;
import com.zalthonethree.zombieinfection.utility.FoodTracking;
import com.zalthonethree.zombieinfection.utility.TimeInfectedTracking;

public class InfectedPlayerUpdateEvent /*extends EntityDragon*/ {
	private static int lastSecond = 0;
	
	@SuppressWarnings("rawtypes") @SubscribeEvent public void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = event.player;
		if (player.isPotionActive(ZombieInfection.potionInfection) && !player.isPotionActive(ZombieInfection.potionCure)) {
			int timeInfected = TimeInfectedTracking.getSecondsInfected(player);
			for (CustomInfectionEffect customEffect : ZombieInfectionAPI.getCustomInfectionEffects()) {
				customEffect.run(player, timeInfected);
			}
			
			player.addPotionEffect(PotionHelper.createInfection(timeInfected < (120) ? 0 : 1));
			player.addPotionEffect(PotionHelper.createHunger(timeInfected < (120) ? 0 : 1));
			player.addPotionEffect(PotionHelper.createSlowness(timeInfected < (120) ? 0 : 1));
			player.addPotionEffect(PotionHelper.createMiningFatigue(timeInfected < (120) ? 0 : 1));
			player.addPotionEffect(PotionHelper.createWeakness(timeInfected < (120) ? 0 : 1));
			if (player.getFoodStats().getFoodLevel() > FoodTracking.get(player)) {
				player.getFoodStats().addStats(FoodTracking.get(player) - player.getFoodStats().getFoodLevel(), 0);
			}
			
			TimeInfectedTracking.update(player);
			FoodTracking.put(player);
			
			if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
				int curSecond = Calendar.getInstance().get(Calendar.SECOND);
				if (curSecond != lastSecond) {
					lastSecond = curSecond;
					TimeInfectedTracking.update(player);
				}
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
			TimeInfectedTracking.remove(player);
			FoodTracking.remove(player);
		}
	}
}
