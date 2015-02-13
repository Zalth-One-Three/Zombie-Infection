package com.zalthonethree.zombieinfection.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.zalthonethree.zombieinfection.Reference;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler /*extends EntityDragon*/ {
	public static Configuration configuration;
	private static int spreadDistance = 3;
	private static int villagerInfectionChance = 25;
	
	private static boolean enableSlowness;
	private static boolean enableHunger;
	private static boolean enableMiningFatigue;
	private static boolean enableWeakness;
	private static boolean enableWither;
	private static boolean enableBurning;
	
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
		spreadDistance = configuration.getInt("Spread Distance", Configuration.CATEGORY_GENERAL, 3, 0, 5, "Distance to be from someone to spread the infection when PVP is off. 0 = Disabled");
		villagerInfectionChance = configuration.getInt("Villager Infection Chance", Configuration.CATEGORY_GENERAL, 25, 0, 100, "Chance for infection to spread to villager upon attack.");
		
		enableSlowness = configuration.getBoolean("Enable Slowness", Configuration.CATEGORY_GENERAL, true, "Infected players get Slowness");
		enableHunger = configuration.getBoolean("Enable Hunger", Configuration.CATEGORY_GENERAL, true, "Infected players get Hunger after 20 seconds");
		enableMiningFatigue = configuration.getBoolean("Enable Mining Fatigue", Configuration.CATEGORY_GENERAL, true, "Infected players get Mining Fatigue after 40 seconds");
		enableWeakness = configuration.getBoolean("Enable Weakness", Configuration.CATEGORY_GENERAL, true, "Infected players get Weakness after 60 seconds");
		enableWither = configuration.getBoolean("Enable Wither", Configuration.CATEGORY_GENERAL, true, "Infected players get Wither after 480 seconds");
		enableBurning = configuration.getBoolean("Enable Burning", Configuration.CATEGORY_GENERAL, true, "Infected players catch fire in sunlight after 80 seconds");
		
		if (configuration.hasChanged()) {
			configuration.save();
		}
	}
	
	public static int getSpreadDistance() { return spreadDistance; }
	public static int getVillagerInfectionChance() { return villagerInfectionChance; }
	public static boolean getSpreadEnabled() { return spreadDistance != 0; }
	
	public static boolean enableSlowness() { return enableSlowness; }
	public static boolean enableHunger() { return enableHunger; }
	public static boolean enableMiningFatigue() { return enableMiningFatigue; }
	public static boolean enableWeakness() { return enableWeakness; }
	public static boolean enableWither() { return enableWither; }
	public static boolean enableBurning() { return enableBurning; }
}