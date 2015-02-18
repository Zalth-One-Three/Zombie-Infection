package com.zalthonethree.zombieinfection.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.zalthonethree.zombieinfection.Reference;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler /*extends EntityDragon*/ {
	public static Configuration configuration;
	
	private static final String CATEGORY_CHANCES = "Chances";
	private static final String CATEGORY_EFFECTS = "Effects";
	
	private static boolean enableEasterEggs = true;
	private static int spreadDistance = 3;
	
	private static int animalInfectionChance = 25;
	private static int playerInfectionChance = 10;
	private static int villagerInfectionChance = 25;
	
	private static int poisonFoodInfectionChance = 10;
	private static int rawFoodInfectionChance = 5;
	private static int spiderEyeInfectionChance = 20;
	
	private static boolean enableBurning = true;
	private static boolean enableHunger = true;
	private static boolean enableMiningFatigue = true;
	private static boolean enableSlowness = true;
	private static boolean enableWeakness = true;
	private static boolean enableWither = true;
	
	
	public static void init(File configFile) {
		if (configuration == null) {
			configuration = new Configuration(configFile);
			loadConfiguration();
		}
	}
	
	@SubscribeEvent public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
			loadConfiguration();
		}
	}
	
	private static void loadConfiguration() {
		enableEasterEggs = configuration.getBoolean("Enable Easter Eggs", Configuration.CATEGORY_GENERAL, true, "Enable/Disable Easter Eggs.");
		spreadDistance = configuration.getInt("Spread Distance", Configuration.CATEGORY_GENERAL, 3, 0, 5, "Distance to be from someone to spread the infection when PVP is off. 0 = Disabled");
		
		animalInfectionChance = configuration.getInt("Animal Infection Chance", CATEGORY_CHANCES, 25, 0, 100, "Chance for infection to spread to animals upon attack.");
		playerInfectionChance = configuration.getInt("Player Infection Chance", CATEGORY_CHANCES, 10, 0, 100, "Chance a zombie will infect a player.");
		villagerInfectionChance = configuration.getInt("Villager Infection Chance", CATEGORY_CHANCES, 25, 0, 100, "Chance for infection to spread to villager upon attack.");
		
		poisonFoodInfectionChance = configuration.getInt("Poison Food Infection Chance", CATEGORY_CHANCES, 10, 0, 100, "Chance poison foods will give infection (E.G Poison Potato, Rotten Flesh)");
		rawFoodInfectionChance = configuration.getInt("Raw Food Infection Chance", CATEGORY_CHANCES, 20, 0, 100, "Chance raw foods will give infection");
		spiderEyeInfectionChance = configuration.getInt("Spider Eye Infection Chance", CATEGORY_CHANCES, 20, 0, 100, "Chance spider eyes will give infection");
		
		enableBurning = configuration.getBoolean("Enable Burning", CATEGORY_EFFECTS, true, "Infected players catch fire in sunlight after 80 seconds.");
		enableHunger = configuration.getBoolean("Enable Hunger", CATEGORY_EFFECTS, true, "Infected players get Hunger after 20 seconds.");
		enableMiningFatigue = configuration.getBoolean("Enable Mining Fatigue", CATEGORY_EFFECTS, true, "Infected players get Mining Fatigue after 40 seconds.");
		enableSlowness = configuration.getBoolean("Enable Slowness", CATEGORY_EFFECTS, true, "Infected players get Slowness.");
		enableWeakness = configuration.getBoolean("Enable Weakness", CATEGORY_EFFECTS, true, "Infected players get Weakness after 60 seconds.");
		enableWither = configuration.getBoolean("Enable Wither", CATEGORY_EFFECTS, true, "Infected players get Wither after 480 seconds.");
		
		if (configuration.hasChanged()) {
			configuration.save();
		}
	}
	
	public static int getSpreadDistance() { return spreadDistance; }
	public static boolean getEasterEggsEnabled() { return enableEasterEggs; }
	public static boolean getSpreadEnabled() { return spreadDistance != 0; }
	
	public static int getAnimalInfectionChance() { return animalInfectionChance; }
	public static int getPlayerInfectionChance() { return playerInfectionChance; }
	public static int getVillagerInfectionChance() { return villagerInfectionChance; }
	
	public static int getPoisonFoodInfectionChance() { return poisonFoodInfectionChance; }
	public static int getRawFoodInfectionChance() { return rawFoodInfectionChance; }
	public static int getSpiderEyeInfectionChance() { return spiderEyeInfectionChance; }
	
	public static boolean enableBurning() { return enableBurning; }
	public static boolean enableHunger() { return enableHunger; }
	public static boolean enableMiningFatigue() { return enableMiningFatigue; }
	public static boolean enableSlowness() { return enableSlowness; }
	public static boolean enableWeakness() { return enableWeakness; }
	public static boolean enableWither() { return enableWither; }
}