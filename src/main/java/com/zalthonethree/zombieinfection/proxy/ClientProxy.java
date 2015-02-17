package com.zalthonethree.zombieinfection.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelPig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;

import com.zalthonethree.zombieinfection.client.gui.GuiEyeInfection;
import com.zalthonethree.zombieinfection.client.model.ModelZombieSheep;
import com.zalthonethree.zombieinfection.client.render.RenderZombieChicken;
import com.zalthonethree.zombieinfection.client.render.RenderZombieCow;
import com.zalthonethree.zombieinfection.client.render.RenderZombiePig;
import com.zalthonethree.zombieinfection.client.render.RenderZombieSheep;
import com.zalthonethree.zombieinfection.entity.EntityZombieChicken;
import com.zalthonethree.zombieinfection.entity.EntityZombieCow;
import com.zalthonethree.zombieinfection.entity.EntityZombiePig;
import com.zalthonethree.zombieinfection.entity.EntityZombieSheep;
import com.zalthonethree.zombieinfection.event.InfectedPlayerTooltipEncryptEvent;
import com.zalthonethree.zombieinfection.updatechecker.UpdateChecker;

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
	
	@Override public void registerRenderers() {
		float shadowSize = 0.5F;
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieCow.class, new RenderZombieCow(Minecraft.getMinecraft().getRenderManager(), new ModelCow(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieChicken.class, new RenderZombieChicken(Minecraft.getMinecraft().getRenderManager(), new ModelChicken(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityZombiePig.class, new RenderZombiePig(Minecraft.getMinecraft().getRenderManager(), new ModelPig(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieSheep.class, new RenderZombieSheep(Minecraft.getMinecraft().getRenderManager(), new ModelZombieSheep(), shadowSize));
	}
}