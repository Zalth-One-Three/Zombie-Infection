package com.zalthonethree.zombieinfection.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import com.zalthonethree.zombieinfection.event.InfectedPlayerUpdateEvent;
import com.zalthonethree.zombieinfection.event.InfectionEvent;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;

public class ServerProxy implements IGuiHandler {
	public void init() {
		MinecraftForge.EVENT_BUS.register(new InfectionEvent());
		FMLCommonHandler.instance().bus().register(new InfectedPlayerUpdateEvent());
	}
	
	public void registerRenderers() {}
	@Override public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { return null; }
	@Override public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { return null; }
}