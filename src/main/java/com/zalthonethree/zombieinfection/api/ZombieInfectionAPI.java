package com.zalthonethree.zombieinfection.api;

import java.util.ArrayList;
import java.util.HashMap;

public class ZombieInfectionAPI /*extends EntityDragon*/ {
	private static ArrayList<CustomInfectionEffect> customInfectionEffects = new ArrayList<CustomInfectionEffect>();
	private static ArrayList<CustomCureEffect> customCureEffects = new ArrayList<CustomCureEffect>();
	private static ArrayList<Integer> customInfectiousMobs = new ArrayList<Integer>();
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
	/**
	 * Register a custom infectious mob.
	 * @param entityId - Internal ID, Refer to minecraft wiki for vanilla mobs
	 * @param infectionChance - If (RNG.nextInt() < infectionChance) then infect
	 */
	public static void registerCustomInfectiousMob(int entityId, int infectionChance) {
		customInfectiousMobs.add(entityId);
		customInfectionChances.put(entityId, infectionChance);
	}
}