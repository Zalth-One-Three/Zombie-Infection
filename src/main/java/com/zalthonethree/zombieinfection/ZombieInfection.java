package com.zalthonethree.zombieinfection;

import com.zalthonethree.zombieinfection.proxy.IProxy;
import com.zalthonethree.zombieinfection.utility.LogHelper;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION) public class ZombieInfection {
	@Mod.Instance(Reference.MOD_ID) public static ZombieInfection instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY) public static IProxy proxy;
	
	@Mod.EventHandler public void preInit(FMLPreInitializationEvent event) {
		// ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		// FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		
		// ModBlocks.init();
		// ModItems.init();
		LogHelper.info("Pre-Init Complete");
	}
	
	@Mod.EventHandler public void init(FMLInitializationEvent event) {
		proxy.init();
		// Recipes.init();
		LogHelper.info("Init Complete");
	}
	
	@Mod.EventHandler public void postInit(FMLPostInitializationEvent event) {
		LogHelper.info("Post-Init Complete");
	}
	
	@Mod.EventHandler public void serverStarting(FMLServerStartingEvent event) {
		proxy.init();
	}
}
