package com.zalthonethree.zombieinfection;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import com.zalthonethree.zombieinfection.handler.ConfigurationHandler;
import com.zalthonethree.zombieinfection.init.ModBlocks;
import com.zalthonethree.zombieinfection.init.ModItems;
import com.zalthonethree.zombieinfection.init.Recipes;
import com.zalthonethree.zombieinfection.proxy.ServerProxy;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, updateJSON = Reference.UPDATE_JSON, acceptedMinecraftVersions = Reference.ACCEPTABLE_MINECRAFT_VERSION) public class ZombieInfection {
	@Mod.Instance(Reference.MOD_ID) public static ZombieInfection instance;
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY) public static ServerProxy proxy;
	
	@Mod.EventHandler public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
		
		proxy.preInit();
	}
	
	@Mod.EventHandler public void init(FMLInitializationEvent event) {
		ModItems.init();
		ModBlocks.init();
		Recipes.init();
		proxy.init();
	}
	
	@Mod.EventHandler public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}
	
	@Mod.EventHandler public void serverStarting(FMLServerStartingEvent event) {
		proxy.init();
	}
}
