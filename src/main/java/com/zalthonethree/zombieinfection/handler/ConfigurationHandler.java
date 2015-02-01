package com.zalthonethree.zombieinfection.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.zalthonethree.zombieinfection.Reference;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {
	public static Configuration configuration;
	private static int spreadDistance = 0;
	private static boolean morphingEnabled = true;
	
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
		morphingEnabled = configuration.getBoolean("Morph Infected", Configuration.CATEGORY_GENERAL, true, "Morph players into zombies when they are infected (Requires iChun's Morph Mod).");
		spreadDistance = configuration.getInt("Spread Distance", Configuration.CATEGORY_GENERAL, 3, 0, 5, "Distance to be from someone to spread the infection when PVP is off. 0 = Disabled");
		if (configuration.hasChanged()) {
			configuration.save();
		}
	}
	
	public static int getSpreadDistance() { return spreadDistance; }
	public static boolean getSpreadEnabled() { return spreadDistance == 0; }
	public static boolean getMorphEnabled() { return morphingEnabled; }
}