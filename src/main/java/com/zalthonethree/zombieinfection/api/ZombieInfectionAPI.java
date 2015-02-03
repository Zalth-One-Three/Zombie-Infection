package com.zalthonethree.zombieinfection.api;

import java.util.ArrayList;

public class ZombieInfectionAPI /*extends EntityDragon*/ {
	private static ArrayList<CustomInfectionEffect> customInfectionEffects = new ArrayList<CustomInfectionEffect>();
	private static ArrayList<CustomCureEffect> customCureEffects = new ArrayList<CustomCureEffect>();
	
	public static ArrayList<CustomInfectionEffect> getCustomInfectionEffects() { return customInfectionEffects; }
	public static void registerCustomInfectionEffect(CustomInfectionEffect customEffect) { customInfectionEffects.add(customEffect); }
	public static ArrayList<CustomCureEffect> getCustomCureEffects() { return customCureEffects; }
	public static void registerCustomCureEffect(CustomCureEffect customCureEffect) { customCureEffects.add(customCureEffect); }
}