package com.zalthonethree.zombieinfection.init;

import net.minecraftforge.fml.common.registry.GameRegistry;

import com.zalthonethree.zombieinfection.item.ItemBase;
import com.zalthonethree.zombieinfection.item.ItemCure;
import com.zalthonethree.zombieinfection.item.ItemInfectedEgg;
import com.zalthonethree.zombieinfection.item.ItemInfectedMilk;
import com.zalthonethree.zombieinfection.item.ItemKnowledgeBook;
import com.zalthonethree.zombieinfection.item.ItemNeedle;

public class ModItems {
	public static final ItemBase cure = new ItemCure();
	public static final ItemBase infectedEgg = new ItemInfectedEgg();
	public static final ItemBase infectedMilk = new ItemInfectedMilk();
	public static final ItemBase knowledgeBook = new ItemKnowledgeBook();
	public static final ItemBase needle = new ItemNeedle();
	
	public static void init() {
		GameRegistry.registerItem(cure, "Cure");
		GameRegistry.registerItem(infectedEgg, "infectedEgg");
		GameRegistry.registerItem(infectedMilk, "infectedMilk");
		GameRegistry.registerItem(knowledgeBook, "knowledgeBook");
		GameRegistry.registerItem(needle, "needle");
	}
}