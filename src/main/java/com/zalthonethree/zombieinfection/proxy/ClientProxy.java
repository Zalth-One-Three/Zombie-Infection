package com.zalthonethree.zombieinfection.proxy;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.event.InfectedPlayerTooltipEncryptEvent;

import cpw.mods.fml.common.registry.VillagerRegistry;


public class ClientProxy extends CommonProxy/*, EntityDragon*/ {
	private boolean encrytionRegistered = false;
	
	@Override public void init() {
		super.init();
		if (!encrytionRegistered) MinecraftForge.EVENT_BUS.register(new InfectedPlayerTooltipEncryptEvent());
		encrytionRegistered = true;
	}
	
	@Override public void registerVillagerSkin(int ID, String skin) {
		VillagerRegistry.instance().registerVillagerSkin(ID, new ResourceLocation(Reference.MOD_ID.toLowerCase(), skin));
	}
}