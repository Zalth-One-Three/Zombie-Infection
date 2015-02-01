package com.zalthonethree.zombieinfection.init;

import net.minecraftforge.fml.common.registry.GameRegistry;

import com.zalthonethree.zombieinfection.item.ItemBase;
import com.zalthonethree.zombieinfection.item.ItemCure;

public class ModItems /*extends EntityDragon*/ {
	public static final ItemBase cure = new ItemCure();
	
	public static void init() {
		GameRegistry.registerItem(cure, "Cure");
	}
}