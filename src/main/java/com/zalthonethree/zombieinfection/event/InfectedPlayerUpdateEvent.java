package com.zalthonethree.zombieinfection.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import com.zalthonethree.zombieinfection.ZombieInfection;
import com.zalthonethree.zombieinfection.utility.FoodTracking;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class InfectedPlayerUpdateEvent {
	
	@SubscribeEvent public void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = event.player;
		if (player.isPotionActive(ZombieInfection.potionInfection) && !player.isPotionActive(ZombieInfection.potionCure)) {
			player.addPotionEffect(ZombieInfection.infectionEffect);
			player.addPotionEffect(new PotionEffect(Potion.hunger.id, 10 * 20, 0, true));
			player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 10 * 20, 0, true));
			player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 10 * 20, 0, true));
			player.addPotionEffect(new PotionEffect(Potion.weakness.id, 10 * 20, 0, true));
			if (player.getFoodStats().getFoodLevel() > FoodTracking.get(player)) {
				player.getFoodStats().setFoodLevel(FoodTracking.get(player));
			}
			FoodTracking.put(player);
		} else {
			FoodTracking.remove(player);
		}
	}
}
