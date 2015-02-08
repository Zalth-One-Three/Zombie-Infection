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
		if (configuration.hasChanged()) {
			configuration.save();
		}
	}
	
	public static int getSpreadDistance() { return spreadDistance; }
	public static int getVillagerInfectionChance() { return villagerInfectionChance; }
	public static boolean getSpreadEnabled() { return spreadDistance != 0; }
}