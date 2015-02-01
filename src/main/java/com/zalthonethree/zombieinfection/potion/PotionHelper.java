package com.zalthonethree.zombieinfection.potion;

import java.util.ArrayList;

import com.zalthonethree.zombieinfection.ZombieInfection;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionHelper {
	private static PotionEffect createNewPotionEffect(int id) {
		PotionEffect effect = new PotionEffect(id, 10 * 20, 0, true);
		effect.setCurativeItems(new ArrayList<ItemStack>());
		return effect;
	}
	
	private static PotionEffect createNewPotionEffect(Potion potion) {
		return createNewPotionEffect(potion.id);
	}
	
	public static PotionEffect createInfection() { return createNewPotionEffect(ZombieInfection.potionInfection); }
	public static PotionEffect createCure() { return createNewPotionEffect(ZombieInfection.potionCure); }
	public static PotionEffect createHunger() { return createNewPotionEffect(Potion.hunger.id); }
	public static PotionEffect createWeakness() { return createNewPotionEffect(Potion.weakness.id); }
	public static PotionEffect createSlowness() { return createNewPotionEffect(Potion.moveSlowdown.id); }
	public static PotionEffect createMiningFatigue() { return createNewPotionEffect(Potion.digSlowdown.id); }
}