package com.zalthonethree.zombieinfection.proxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

import com.zalthonethree.zombieinfection.client.gui.GuiEyeInfection;
import com.zalthonethree.zombieinfection.event.InfectedPlayerTooltipEncryptEvent;
import com.zalthonethree.zombieinfection.updatechecker.UpdateChecker;

import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy/*, EntityDragon*/ {
	private boolean encrytionRegistered = false;
	private boolean updateEventRegistered = false;
	
	@Override public void init() {
		super.init();
		MinecraftForge.EVENT_BUS.register(new GuiEyeInfection(Minecraft.getMinecraft()));
		if (!updateEventRegistered) {
			MinecraftForge.EVENT_BUS.register(new UpdateChecker());
			FMLCommonHandler.instance().bus().register(new UpdateChecker());
			updateEventRegistered = true;
		}
		if (!encrytionRegistered) MinecraftForge.EVENT_BUS.register(new InfectedPlayerTooltipEncryptEvent());
		encrytionRegistered = true;
	}
}