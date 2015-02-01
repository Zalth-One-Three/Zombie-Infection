package com.zalthonethree.zombieinfection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import com.zalthonethree.zombieinfection.init.ModItems;
import com.zalthonethree.zombieinfection.init.Recipes;
import com.zalthonethree.zombieinfection.potion.PotionCure;
import com.zalthonethree.zombieinfection.potion.PotionInfection;
import com.zalthonethree.zombieinfection.proxy.IProxy;
import com.zalthonethree.zombieinfection.utility.LogHelper;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION) public class ZombieInfection /*extends EntityDragon*/ {
	@Mod.Instance(Reference.MOD_ID) public static ZombieInfection instance;
	public static Potion potionInfection;
	public static Potion potionCure;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY) public static IProxy proxy;
	
	@Mod.EventHandler public void preInit(FMLPreInitializationEvent event) {
		// ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		// FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		Potion[] potionTypes = null;
		
		for (Field f : Potion.class.getDeclaredFields()) {
			f.setAccessible(true);
			
			try {
				if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
					Field modfield = Field.class.getDeclaredField("modifiers");
					modfield.setAccessible(true);
					modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);
					potionTypes = (Potion[]) f.get(null);
					final Potion[] newPotionTypes = new Potion[256];
					System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
					f.set(null, newPotionTypes);
				}
			} catch (Exception e) {
				System.err.println("Severe error, please report this to the mod author:");
				System.err.println(e);
			}
		}
		
		ModItems.init();
		LogHelper.info("Pre-Init Complete");
	}
	
	@Mod.EventHandler public void init(FMLInitializationEvent event) {
		proxy.init();
		
		potionInfection = (new PotionInfection(63, new ResourceLocation("hunger"), true, 0)).setIconIndex(3, 1).setPotionName("Infection");
		potionCure = (new PotionCure(64, new ResourceLocation("absorption"), true, 0)).setIconIndex(2, 2).setPotionName("Infection Cure");
		
		Recipes.init();
		LogHelper.info("Init Complete");
	}
	
	@Mod.EventHandler public void postInit(FMLPostInitializationEvent event) {
		LogHelper.info("Post-Init Complete");
	}
	
	@Mod.EventHandler public void serverStarting(FMLServerStartingEvent event) {
		proxy.init();
	}
}
