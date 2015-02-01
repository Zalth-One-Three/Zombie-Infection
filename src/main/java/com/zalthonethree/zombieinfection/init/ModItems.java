package com.zalthonethree.zombieinfection.init;

import com.zalthonethree.zombieinfection.item.ItemBase;
import com.zalthonethree.zombieinfection.item.ItemCure;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems /*extends EntityDragon*/ {
	public static final ItemBase cure = new ItemCure();
	
	public static void init() {
		GameRegistry.registerItem(cure, "Cure");
	}
}