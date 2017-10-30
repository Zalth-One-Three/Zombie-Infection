package com.zalthonethree.zombieinfection.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.zalthonethree.zombieinfection.Reference;

public class ConfigurationHandler {
	public static Configuration configuration;
	
	private static final String CATEGORY_CHANCES = "Chances";
	private static final String CATEGORY_TIMERS = "Timers";
	private static final String CATEGORY_EFFECTS = "Effects";
	private static final String CATEGORY_EFFECTS_2 = "Upgraded Effects";

	private static boolean enableEasterEggs = true;
	private static int spreadDistance = 3;
	
	private static int animalInfectionChance = 25;
	private static int playerInfectionChance = 10;
	private static int villagerInfectionChance = 25;
	
	private static int poisonFoodInfectionChance = 10;
	private static int rawFoodInfectionChance = 5;
	private static int spiderEyeInfectionChance = 20;

	private static int burningTime = 300;
	private static int hungerTime = 300;
	private static int upgradedHungerTime = 390;
	private static int miningFatigueTime = 150;
	private static int upgradedMiningFatigueTime = 280;
	private static int slownessTime = 105;
	private static int upgradedSlownessTime = 260;
	private static int weaknessTime = 60;
	private static int upgradedWeaknessTime = 225;

	private static boolean enableBurning = true;
	private static boolean enableHunger = true;
	private static boolean enableMiningFatigue = true;
	private static boolean enableSlowness = true;
	private static boolean enableWeakness = true;
	private static boolean enableWither = true;

	private static int enableUpgradedHunger = 1;
	private static int enableUpgradedMiningFatigue = 1;
	private static int enableUpgradedSlowness = 1;
	private static int enableUpgradedWeakness = 1;

	
	public static void init(File configFile) {
		if (configuration == null) {
			configuration = new Configuration(configFile);
			loadConfiguration();
		}
	}
	
	@SubscribeEvent public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equalsIgnoreCase(Reference.MOD_ID)) {
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

		burningTime = configuration.getInt("Timer: Burning Effect", CATEGORY_TIMERS, 300, 0, 480, "How many seconds it will take before the player catches on fire on the sunlight");
		hungerTime = configuration.getInt("Timer: Hunger Level 1", CATEGORY_TIMERS, 300, 0, 480, "How many seconds it will take before the player gets the hunger effect (level 1)");
		upgradedHungerTime = configuration.getInt("Timer: Hunger Level 2", CATEGORY_TIMERS, 390, 0, 480, "How many seconds it will take before the player gets the upgraded hunger effect (level 2), will only trigger once the level 1 version is active");
		miningFatigueTime = configuration.getInt("Timer: Mining Fatigue Level 1", CATEGORY_TIMERS, 150, 0, 480, "How many seconds it will take before the player gets the mining fatigue effect (level 1)");
		upgradedMiningFatigueTime = configuration.getInt("Timer: Mining Fatigue Level 2", CATEGORY_TIMERS, 280, 0, 480, "How many seconds it will take before the player gets the upgraded mining fatigue effect (level 2), will only trigger once the level 1 version is active");
		slownessTime = configuration.getInt("Timer: Slowness Level 1", CATEGORY_TIMERS, 0, 105, 480, "How many seconds it will take before the player gets the slowness effect (level 1)");
		upgradedSlownessTime = configuration.getInt("Timer: Slowness Level 2", CATEGORY_TIMERS, 260, 0, 480, "How many seconds it will take before the player gets the upgraded slowness effect (level 2), will only trigger once the level 1 version is active");
		weaknessTime = configuration.getInt("Timer: Weakness Level 1", CATEGORY_TIMERS, 60, 0, 480, "How many seconds it will take before the player gets the weakness effect (level 1)");
		upgradedWeaknessTime = configuration.getInt("Timer: Weakness Level 2", CATEGORY_TIMERS, 225, 0, 480, "How many seconds it will take before the player gets the upgraded weakness effect (level 2), will only trigger once the level 1 version is active");

		enableBurning = configuration.getBoolean("Enable Burning", CATEGORY_EFFECTS, true, "Infected players catch fire in sunlight based on the values set.");
		enableHunger = configuration.getBoolean("Enable Hunger", CATEGORY_EFFECTS, true, "Infected players get Hunger based on the values set.");
		enableMiningFatigue = configuration.getBoolean("Enable Mining Fatigue", CATEGORY_EFFECTS, true, "Infected players get Mining Fatigue based on the values set.");
		enableSlowness = configuration.getBoolean("Enable Slowness", CATEGORY_EFFECTS, true, "Infected players get Slowness based on the values set.");
		enableWeakness = configuration.getBoolean("Enable Weakness", CATEGORY_EFFECTS, true, "Infected players get Weakness based on the values set.");
		enableWither = configuration.getBoolean("Enable Wither", CATEGORY_EFFECTS, true, "Infected players get Wither after 480 seconds.");

		enableUpgradedHunger = configuration.getInt("Enable Upgraded Hunger", CATEGORY_EFFECTS_2, 1, 0, 1, "Enable or Disable Hunger Level 2, (0 = Disabled, 1 = Enabled)");
		enableUpgradedMiningFatigue = configuration.getInt("Enable Upgraded Mining Fatigue", CATEGORY_EFFECTS_2, 1, 0, 1, "Enable or Disable Mining Fatigue Level 2, (0 = Disabled, 1 = Enabled)");
		enableUpgradedSlowness = configuration.getInt("Enable Upgraded Slowness", CATEGORY_EFFECTS_2, 1, 0, 1, "Enable or Disable Slowness Level 2, (0 = Disabled, 1 = Enabled)");
		enableUpgradedWeakness = configuration.getInt("Enable Upgraded Weakness", CATEGORY_EFFECTS_2, 1, 0, 1, "Enable or Disable Weakness Level 2, (0 = Disabled, 1 = Enabled)");

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

	public static int getBurningTime() { return burningTime; }
	public static int getHungerTime() { return hungerTime; }
	public static int getUpgradedHungerTime() { return upgradedHungerTime; }
	public static int getMiningFatigueTime() { return miningFatigueTime; }
	public static int getUpgradedMiningFatigueTime() { return upgradedMiningFatigueTime; }
	public static int getSlownessTime() { return slownessTime; }
	public static int getUpgradedSlownessTime() { return upgradedSlownessTime; }
	public static int getWeaknessTime() { return weaknessTime; }
	public static int getUpgradedWeaknessTime() { return upgradedWeaknessTime; }

	public static boolean enableBurning() { return enableBurning; }
	public static boolean enableHunger() { return enableHunger; }
	public static boolean enableMiningFatigue() { return enableMiningFatigue; }
	public static boolean enableSlowness() { return enableSlowness; }
	public static boolean enableWeakness() { return enableWeakness; }
	public static boolean enableWither() { return enableWither; }

	public static int enableUpgradedHunger() { return enableUpgradedHunger; }
	public static int enableUpgradedMiningFatigue() { return enableUpgradedMiningFatigue; }
	public static int enableUpgradedSlowness() { return enableUpgradedSlowness; }
	public static int enableUpgradedWeakness() { return enableUpgradedWeakness; }
}