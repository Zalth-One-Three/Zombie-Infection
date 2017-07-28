package com.zalthonethree.zombieinfection.init;

import com.zalthonethree.zombieinfection.item.ItemBase;
import com.zalthonethree.zombieinfection.item.ItemCure;
import com.zalthonethree.zombieinfection.item.ItemInfectedEgg;
import com.zalthonethree.zombieinfection.item.ItemInfectedMilk;
import com.zalthonethree.zombieinfection.item.ItemKnowledgeBook;
import com.zalthonethree.zombieinfection.item.ItemNeedle;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber()
public class ModRegistry {
	public static final ItemBase CURE = new ItemCure();
	public static final ItemBase INFECTED_EGG = new ItemInfectedEgg();
	public static final ItemBase INFECTED_MILK = new ItemInfectedMilk();
	public static final ItemBase KNOWLEDGE_BOOK = new ItemKnowledgeBook();
	public static final ItemBase NEEDLE = new ItemNeedle();
	
	@SubscribeEvent public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(CURE);
		registry.register(INFECTED_EGG);
		registry.register(INFECTED_MILK);
		registry.register(KNOWLEDGE_BOOK);
		registry.register(NEEDLE);
	}
}