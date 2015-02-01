package com.zalthonethree.zombieinfection.proxy;

import com.zalthonethree.zombieinfection.event.InfectedPlayerTooltipEncryptEvent;

import net.minecraftforge.common.MinecraftForge;


public class ClientProxy extends CommonProxy/*, EntityDragon*/ {
	private boolean encrytionRegistered = false;
	
	@Override public void init() {
		super.init();
		if (!encrytionRegistered) MinecraftForge.EVENT_BUS.register(new InfectedPlayerTooltipEncryptEvent());
		encrytionRegistered = true;
	}
}