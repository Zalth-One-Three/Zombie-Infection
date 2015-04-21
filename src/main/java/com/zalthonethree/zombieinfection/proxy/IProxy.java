package com.zalthonethree.zombieinfection.proxy;

import cpw.mods.fml.common.network.IGuiHandler;

public interface IProxy extends IGuiHandler/*, EntityDragon*/ {
	public abstract void init();
	public abstract void registerRenderers();
}