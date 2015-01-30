package com.zalthonethree.zombieinfection.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class InfectionEvent {
	@SubscribeEvent
	public void onZombieAttack(LivingHurtEvent event) {
		if (event.source instanceof EntityDamageSource) {
			EntityDamageSource source = (EntityDamageSource) event.source;
			Entity attacker = source.getEntity();
			if (attacker instanceof EntityZombie) {
				Entity target = event.entity;
				if (target instanceof EntityPlayer) {
					EntityPlayer attacked = (EntityPlayer) target;
					if ((attacked.getRNG().nextInt(100) + 1) <= 20) {
						attacked.addChatMessage(new ChatComponentText("INFECTED!"));
						//TODO: INFECT
					}
				}
			}
		}
	}
}