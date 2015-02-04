package com.zalthonethree.zombieinfection.api;

import java.util.ArrayList;
import java.util.HashMap;

public class ZombieInfectionAPI /*extends EntityDragon*/ {
	private static ArrayList<CustomInfectionEffect> customInfectionEffects = new ArrayList<CustomInfectionEffect>();
	private static ArrayList<CustomCureEffect> customCureEffects = new ArrayList<CustomCureEffect>();
	private static ArrayList<Integer> customInfectiousMobs = new ArrayList<Integer>();
	private static ArrayList<String> encryptionExclusions = new ArrayList<String>();
	private static HashMap<Integer, Integer> customInfectionChances = new HashMap<Integer, Integer>();
	
	public static ArrayList<CustomInfectionEffect> getCustomInfectionEffects() { return customInfectionEffects; }
	/**
	 * Create a new instance of Custom Cure Effect and override the run(EntityPlayer player, ItemStack stack) methods
	 * @param customEffect
	 */
	public static void registerCustomInfectionEffect(CustomInfectionEffect customEffect) { customInfectionEffects.add(customEffect); }
	public static ArrayList<CustomCureEffect> getCustomCureEffects() { return customCureEffects; }
	/**
	 * Create a new instance of Custom Cure Effect and override the run(EntityPlayer player, ItemStack stack) method
	 * @param customCureEffect
	 */
	public static void registerCustomCureEffect(CustomCureEffect customCureEffect) { customCureEffects.add(customCureEffect); }
	public static ArrayList<Integer> getCustionInfectiousMobs() { return customInfectiousMobs; }
	public static HashMap<Integer, Integer> getCustomInfectionChances() { return customInfectionChances; }
	/**
	 * Register a custom infectious mob.
	 * @param entityId - Internal ID, Refer to minecraft wiki for vanilla mobs
	 * @param infectionChance - If (RNG.nextInt(100) + 1 < infectionChance) then infect
	 */
	public static void registerCustomInfectiousMob(int entityId, int infectionChance) {
		customInfectiousMobs.add(entityId);
		customInfectionChances.put(entityId, infectionChance);
	}
	public static ArrayList<String> getEncryptionExclusions() { return encryptionExclusions; }
	/**
	 * Prevents an item's tooltip from being scrambled when infected.
	 * @param unlocalizedName - Unlocalized name of the item to prevent being scrambled.
	 */
	public static void registerEncryptionExclusion(String unlocalizedName) {
		encryptionExclusions.add(unlocalizedName);
	}
}