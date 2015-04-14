package com.zalthonethree.zombieinfection.event;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;

import com.zalthonethree.zombieinfection.ZombieInfection;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class InfectedPlayerRenderEvent {
/*	@SubscribeEvent public void onPlayerRender(RenderPlayerEvent.Pre event) {
		EntityPlayer player = event.entityPlayer;
		if (player.isPotionActive(ZombieInfection.potionInfection)) {
			EntityZombie zombie = new EntityZombie(player.worldObj);
			zombie.posX = player.posX;
			zombie.posY = player.posY;
			zombie.posZ = player.posZ;
			zombie.rotationPitch = player.rotationPitch;
			zombie.rotationYaw = player.rotationYaw;
			zombie.rotationYawHead = player.rotationYawHead;
			RenderManager.instance.doRenderEntity(zombie, 0, 0, 0, player.rotationYaw, event.partialRenderTick, false);
			event.setCanceled(true);
		}
	}*/
}