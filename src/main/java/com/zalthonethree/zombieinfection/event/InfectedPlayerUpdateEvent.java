package com.zalthonethree.zombieinfection.event;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import com.zalthonethree.zombieinfection.ZombieInfection;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class InfectedPlayerUpdateEvent {
	
	@SubscribeEvent public void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
		if (event.player.isPotionActive(ZombieInfection.potionInfection) && !event.player.isPotionActive(ZombieInfection.potionCure)) {
			event.player.addPotionEffect(ZombieInfection.infectionEffect);
			event.player.addPotionEffect(new PotionEffect(Potion.confusion.id, 10 * 20, 0, true));
		}
	}
}
