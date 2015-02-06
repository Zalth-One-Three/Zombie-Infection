package com.zalthonethree.zombieinfection.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.zalthonethree.zombieinfection.Reference;

public class ConfigurationHandler /*extends EntityDragon*/ {
	public static Configuration configuration;
	private static int spreadDistance = 0;
	private static int villagerInfectionChance = 25;
	private static int villagerId = 65;
	
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
		villagerId = configuration.getInt("Villager ID", Configuration.CATEGORY_GENERAL, 65, 7, Integer.MAX_VALUE, "ID of custom villager, change if conflicting with another mod.");
		if (configuration.hasChanged()) {
			configuration.save();
		}
	}
	
	public static int getSpreadDistance() { return spreadDistance; }
	public static int getVillagerInfectionChance() { return villagerInfectionChance; }
	public static int getVillagerID() { return villagerId; }
	public static boolean getSpreadEnabled() { return spreadDistance == 0; }
}