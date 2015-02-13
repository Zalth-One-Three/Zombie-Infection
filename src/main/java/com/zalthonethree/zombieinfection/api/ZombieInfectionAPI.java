package com.zalthonethree.zombieinfection.api;

import java.util.ArrayList;
import java.util.HashMap;

public class ZombieInfectionAPI /*extends EntityDragon*/ {
	private static ArrayList<CustomInfectionEffect> customInfectionEffects = new ArrayList<CustomInfectionEffect>();
	private static ArrayList<CustomCureEffect> customCureEffects = new ArrayList<CustomCureEffect>();
	private static ArrayList<Integer> customInfectiousMobs = new ArrayList<Integer>();
	private static ArrayList<String> encryptionExclusions = new ArrayList<String>();
	private static HashMap<Integer, Integer> customInfectionChances = new HashMap<Integer, Integer>();
	private static HashMap<String, String> encryptionSwitches = new HashMap<String, String>();
	private static HashMap<String, String[]> encryptionSwitchesTooltips = new HashMap<String, String[]>();
	private static HashMap<String, Integer> customFoodInfections = new HashMap<String, Integer>();
	
	public static ArrayList<CustomInfectionEffect> getCustomInfectionEffects() { return customInfectionEffects; }
	public static ArrayList<CustomCureEffect> getCustomCureEffects() { return customCureEffects; }
	public static ArrayList<Integer> getCustionInfectiousMobs() { return customInfectiousMobs; }
	public static HashMap<Integer, Integer> getCustomInfectionChances() { return customInfectionChances; }
	public static ArrayList<String> getEncryptionExclusions() { return encryptionExclusions; }
	public static HashMap<String, String> getEncryptionSwitches() { return encryptionSwitches; }
	public static HashMap<String, String[]> getEncryptionSwitchesTooltips() { return encryptionSwitchesTooltips; }
	public static HashMap<String, Integer> getCustomFoodInfections() { return customFoodInfections; }
	
	
	/**
	 * Create a new instance of Custom Cure Effect and override the run(EntityPlayer player, ItemStack stack) methods
	 * @param customEffect
	 */
	public static void registerCustomInfectionEffect(CustomInfectionEffect customEffect) { customInfectionEffects.add(customEffect); }
	
	/**
	 * Create a new instance of Custom Cure Effect and override the run(EntityPlayer player, ItemStack stack) method
	 * @param customCureEffect
	 */
	public static void registerCustomCureEffect(CustomCureEffect customCureEffect) { customCureEffects.add(customCureEffect); }
	
	/**
	 * Register a custom infectious mob.
	 * @param entityId - Internal ID, Refer to minecraft wiki for vanilla mobs
	 * @param infectionChance - If (RNG.nextInt(100) + 1 < infectionChance) then infect
	 */
	public static void registerCustomInfectiousMob(int entityId, int infectionChance) {
		customInfectiousMobs.add(entityId);
		customInfectionChances.put(entityId, infectionChance);
	}
	
	/**
	 * Prevents an item's tooltip from being scrambled when infected.
	 * @param unlocalizedName - Unlocalized name of the item to prevent being scrambled.
	 */
	public static void registerEncryptionExclusion(String unlocalizedName) {
		encryptionExclusions.add(unlocalizedName);
	}
	
	/**
	 * Turns one item name into a new item name.
	 * @param unlocalizedName - Old unlocalized name. E.G item.apple
	 * @param newUnlocalizedName - New unlocalized name. E.G easteregg.cow
	 */
	public static void registerEncryptionSwitch(String unlocalizedName, String newUnlocalizedName) {
		encryptionSwitches.put(unlocalizedName, newUnlocalizedName);
	}
	
	/**
	 * Turns one item name into a new item name. Tooltips are added in an unlocalized form.
	 * @param unlocalizedName - Old unlocalized name. E.G item.apple
	 * @param newUnlocalizedName - New unlocalized name. E.G easteregg.cow
	 * @param toolTips - Infinite amount of strings to add to the tooltip.
	 */
	public static void registerEncryptionSwitch(String unlocalizedName, String newUnlocalizedName, String... toolTips) {
		encryptionSwitches.put(unlocalizedName, newUnlocalizedName);
		encryptionSwitchesTooltips.put(unlocalizedName, toolTips);
	}
	
	/**
	 * Registers items that when finished using have a chance of infecting the player.
	 * @param unlocalizedName - Unlocalized name of item to infect the player.
	 * @param infectionChance - Chance of the item infecting the player.
	 */
	public static void registerFoodInfection(String unlocalizedName, int infectionChance) {
		customFoodInfections.put(unlocalizedName, infectionChance);
	}
}