package com.zalthonethree.zombieinfection.init;

import com.zalthonethree.zombieinfection.api.ZombieInfectionAPI;
import com.zalthonethree.zombieinfection.handler.ConfigurationHandler;

public class EasterEggs {
	public static void init() {
		if (ConfigurationHandler.getEasterEggsEnabled()) {
			ZombieInfectionAPI.registerEncryptionSwitch("item.mushroomStew", "easteregg.stew", "easteregg.description.stew");
			ZombieInfectionAPI.registerEncryptionSwitch("item.egg", "easteregg.egg", "easteregg.description.egg");
			ZombieInfectionAPI.registerEncryptionSwitch("block.sponge", "easteregg.sponge", "easteregg.description.sponge");
			ZombieInfectionAPI.registerEncryptionSwitch("item.cooked_porkchop", "easteregg.cooked_porkchop", "easteregg.description.cooked_porkchop");
		}
	}
}