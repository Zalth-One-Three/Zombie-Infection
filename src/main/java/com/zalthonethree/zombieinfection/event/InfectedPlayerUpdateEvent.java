package com.zalthonethree.zombieinfection.event;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import com.zalthonethree.zombieinfection.ZombieInfection;
import com.zalthonethree.zombieinfection.potion.PotionHelper;
import com.zalthonethree.zombieinfection.utility.FoodTracking;

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
				player.getFoodStats().setFoodLevel(FoodTracking.get(player));
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
