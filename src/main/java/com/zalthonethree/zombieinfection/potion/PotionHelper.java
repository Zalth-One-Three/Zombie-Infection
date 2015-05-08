package com.zalthonethree.zombieinfection.potion;

import java.util.ArrayList;

import com.zalthonethree.zombieinfection.ZombieInfection;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionHelper /*extends EntityDragon*/ {
	private static PotionEffect createNewPotionEffect(int id, int level) {
		PotionEffect effect = new PotionEffect(id, (10 * 20) + 10, level, true);
		effect.setCurativeItems(new ArrayList<ItemStack>());
		return effect;
	}
	
	private static PotionEffect createNewPotionEffect(Potion potion, int level) {
		return createNewPotionEffect(potion.id, level);
	}
	
	public static PotionEffect createInfection(int level) { return createNewPotionEffect(ZombieInfection.potionInfection, level); }
	public static PotionEffect createCure(int level) { return createNewPotionEffect(ZombieInfection.potionCure, level); }
	public static PotionEffect createHunger(int level) { return createNewPotionEffect(Potion.hunger.id, level); }
	public static PotionEffect createWeakness(int level) { return createNewPotionEffect(Potion.weakness.id, level); }
	public static PotionEffect createSlowness(int level) { return createNewPotionEffect(Potion.moveSlowdown.id, level); }
	public static PotionEffect createMiningFatigue(int level) { return createNewPotionEffect(Potion.digSlowdown.id, level); }
	public static PotionEffect createWither(int level) { return createNewPotionEffect(Potion.wither.id, level); }
}