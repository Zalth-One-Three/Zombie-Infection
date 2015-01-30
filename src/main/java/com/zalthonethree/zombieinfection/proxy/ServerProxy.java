package com.zalthonethree.zombieinfection.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.IGuiHandler;

import com.zalthonethree.zombieinfection.event.InfectedPlayerUpdateEvent;
import com.zalthonethree.zombieinfection.event.InfectionEvent;

public class ServerProxy implements IGuiHandler {
	public void init() {
		MinecraftForge.EVENT_BUS.register(new InfectionEvent());
		MinecraftForge.EVENT_BUS.register(new InfectedPlayerUpdateEvent());
	}
	
	public void registerRenderers() {}
	@Override public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { return null; }
	@Override public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { return null; }
}