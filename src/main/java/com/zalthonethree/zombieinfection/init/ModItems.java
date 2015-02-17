package com.zalthonethree.zombieinfection.init;

import com.zalthonethree.zombieinfection.item.ItemBase;
import com.zalthonethree.zombieinfection.item.ItemCure;
import com.zalthonethree.zombieinfection.item.ItemInfectedEgg;
import com.zalthonethree.zombieinfection.item.ItemInfectedMilk;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems /*extends EntityDragon*/ {
	public static final ItemBase cure = new ItemCure();
	public static final ItemBase infectedEgg = new ItemInfectedEgg();
	public static final ItemBase infectedMilk = new ItemInfectedMilk();
	
	public static void init() {
		GameRegistry.registerItem(cure, "Cure");
		GameRegistry.registerItem(infectedEgg, "infectedEgg");
		GameRegistry.registerItem(infectedMilk, "infectedMilk");
	}
}