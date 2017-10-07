package com.zalthonethree.zombieinfection.init;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.item.ItemBase;
import com.zalthonethree.zombieinfection.item.ItemCure;
import com.zalthonethree.zombieinfection.item.ItemInfectedEgg;
import com.zalthonethree.zombieinfection.item.ItemInfectedMilk;
import com.zalthonethree.zombieinfection.item.ItemKnowledgeBook;
import com.zalthonethree.zombieinfection.item.ItemNeedle;
import com.zalthonethree.zombieinfection.potion.ModPotion.PotionZI;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
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
	
	public static final PotionZI POTION_INFECTION = new PotionZI(new ResourceLocation(Reference.MOD_ID, "infection"), new ResourceLocation(Reference.MOD_ID, "potion/infection.png"), false, 0xff00ff);
	public static final PotionZI POTION_CURE = new PotionZI(new ResourceLocation(Reference.MOD_ID, "cure"), new ResourceLocation(Reference.MOD_ID, "potion/cure.png"), false, 0xff00ff);
	
	@SubscribeEvent public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(CURE);
		registry.register(INFECTED_EGG);
		registry.register(INFECTED_MILK);
		registry.register(KNOWLEDGE_BOOK);
		registry.register(NEEDLE);
	}
	
	@SubscribeEvent public static void registerPotions(RegistryEvent.Register<Potion> event) {
		IForgeRegistry<Potion> registry = event.getRegistry();
		
		registry.register(POTION_INFECTION);
		registry.register(POTION_CURE);
	}
}