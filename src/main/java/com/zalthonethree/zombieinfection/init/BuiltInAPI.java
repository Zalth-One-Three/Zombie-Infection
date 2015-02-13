package com.zalthonethree.zombieinfection.init;

import com.zalthonethree.zombieinfection.api.ZombieInfectionAPI;
import com.zalthonethree.zombieinfection.handler.ConfigurationHandler;

public class BuiltInAPI /*extends EntityDragon, Laziness*/ {
	public static void init() {
		int poisonChance = ConfigurationHandler.getPoisonFoodInfectionChance();
		int rawChance = ConfigurationHandler.getRawFoodInfectionChance();
		ZombieInfectionAPI.registerFoodInfection("item.spiderEye", ConfigurationHandler.getSpiderEyeInfectionChance());
		ZombieInfectionAPI.registerFoodInfection("item.rottenFlesh", poisonChance);
		ZombieInfectionAPI.registerFoodInfection("item.potatoPoisonous", poisonChance);
		ZombieInfectionAPI.registerFoodInfection("item.porkchopRaw", rawChance);
		ZombieInfectionAPI.registerFoodInfection("item.fish.cod.raw", rawChance);
		ZombieInfectionAPI.registerFoodInfection("item.fish.pufferfish.raw", rawChance);
		ZombieInfectionAPI.registerFoodInfection("item.beef.raw", rawChance);
		ZombieInfectionAPI.registerFoodInfection("item.chicken.raw", rawChance);
		
		ZombieInfectionAPI.registerCustomInfectiousMob(54, ConfigurationHandler.getPlayerInfectionChance());
	}
}