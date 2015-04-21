package com.zalthonethree.zombieinfection.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ServerProxy extends CommonProxy/*, EntityDragon*/ {
	@Override public void init() {
		super.init();
	}
	
	@Override public void registerRenderers() {}

	@Override public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}
}