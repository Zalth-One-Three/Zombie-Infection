package com.zalthonethree.zombieinfection.potion;

import java.util.ArrayList;

import com.zalthonethree.zombieinfection.init.ModRegistry;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionHelper {
	private static PotionEffect createNewPotionEffect(Potion potion, int level) {
		PotionEffect effect = new PotionEffect(potion, (10 * 20) + 10, level, true, true);
		effect.setCurativeItems(new ArrayList<ItemStack>());
		return effect;
	}
	
	public static PotionEffect createInfection(int level) { return createNewPotionEffect(ModRegistry.POTION_INFECTION, level); }
	public static PotionEffect createCure(int level) { return createNewPotionEffect(ModRegistry.POTION_CURE, level); }
	public static PotionEffect createHunger(int level) { return createNewPotionEffect(Potion.getPotionFromResourceLocation("hunger"), level); }
	public static PotionEffect createWeakness(int level) { return createNewPotionEffect(Potion.getPotionFromResourceLocation("weakness"), level); }
	public static PotionEffect createSlowness(int level) { return createNewPotionEffect(Potion.getPotionFromResourceLocation("slowness"), level); }
	public static PotionEffect createMiningFatigue(int level) { return createNewPotionEffect(Potion.getPotionFromResourceLocation("mining_fatigue"), level); }
	public static PotionEffect createWither(int level) { return createNewPotionEffect(Potion.getPotionFromResourceLocation("wither"), level); }
}