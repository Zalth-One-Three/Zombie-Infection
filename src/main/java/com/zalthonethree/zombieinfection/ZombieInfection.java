package com.zalthonethree.zombieinfection;

import com.zalthonethree.zombieinfection.handler.ConfigurationHandler;
import com.zalthonethree.zombieinfection.handler.PacketHandler;
import com.zalthonethree.zombieinfection.init.BuiltInAPI;
import com.zalthonethree.zombieinfection.init.EasterEggs;
import com.zalthonethree.zombieinfection.init.EntityInit;
import com.zalthonethree.zombieinfection.proxy.ServerProxy;
import com.zalthonethree.zombieinfection.utility.LogHelper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, updateJSON = "https://raw.githubusercontent.com/Zalth-One-Three/Zombie-Infection/versions/versions.json") public class ZombieInfection {
	@Mod.Instance(Reference.MOD_ID) public static ZombieInfection instance;
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY) public static ServerProxy proxy;
	
	@Mod.EventHandler public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
		
		PacketHandler.INSTANCE.ordinal();
		LogHelper.info("Pre-Init Complete");
	}
	
	@Mod.EventHandler public void init(FMLInitializationEvent event) {
		proxy.init();
		proxy.registerRenderers();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		EasterEggs.init();
		EntityInit.init();
		BuiltInAPI.init();
		LogHelper.info("Init Complete");
	}
	
	@Mod.EventHandler public void postInit(FMLPostInitializationEvent event) {
		LogHelper.info("Post-Init Complete");
	}
	
	@Mod.EventHandler public void serverStarting(FMLServerStartingEvent event) {
		proxy.init();
	}
}
