package com.zalthonethree.zombieinfection.potion;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import com.zalthonethree.zombieinfection.ZombieInfection;

public class PotionHelper {
	private static PotionEffect createNewPotionEffect(int id, int level) {
		PotionEffect effect = new PotionEffect(id, 10 * 20, level, true, true);
		effect.setCurativeItems(new ArrayList<ItemStack>());
		return effect;
	}
	
	private static PotionEffect createNewPotionEffect(int id) {
		return createNewPotionEffect(id, 0);
	}
	
	private static PotionEffect createNewPotionEffect(Potion potion) {
		return createNewPotionEffect(potion.id);
	}
	
	public static PotionEffect createInfection() { return createNewPotionEffect(ZombieInfection.potionInfection); }
	public static PotionEffect createCure() { return createNewPotionEffect(ZombieInfection.potionCure); }
	public static PotionEffect createHunger() { return createNewPotionEffect(Potion.hunger.id); }
	public static PotionEffect createWeakness() { return createNewPotionEffect(Potion.weakness.id); }
	public static PotionEffect createSlowness(int level) { return createNewPotionEffect(Potion.moveSlowdown.id, level); }
	public static PotionEffect createMiningFatigue(int level) { return createNewPotionEffect(Potion.digSlowdown.id, level); }
}