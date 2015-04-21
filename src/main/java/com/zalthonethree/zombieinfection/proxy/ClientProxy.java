package com.zalthonethree.zombieinfection.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import com.zalthonethree.zombieinfection.Reference.IdTracking;
import com.zalthonethree.zombieinfection.client.gui.GuiEyeInfection;
import com.zalthonethree.zombieinfection.client.gui.KnowledgeBook;
import com.zalthonethree.zombieinfection.client.model.ModelZombieSheep;
import com.zalthonethree.zombieinfection.client.render.RenderZombieChicken;
import com.zalthonethree.zombieinfection.client.render.RenderZombieCow;
import com.zalthonethree.zombieinfection.client.render.RenderZombiePig;
import com.zalthonethree.zombieinfection.client.render.RenderZombieSheep;
import com.zalthonethree.zombieinfection.entity.EntityZombieChicken;
import com.zalthonethree.zombieinfection.entity.EntityZombieCow;
import com.zalthonethree.zombieinfection.entity.EntityZombiePig;
import com.zalthonethree.zombieinfection.entity.EntityZombieSheep;
import com.zalthonethree.zombieinfection.event.InfectedPlayerRenderEvent;
import com.zalthonethree.zombieinfection.event.InfectedPlayerTooltipEncryptEvent;
import com.zalthonethree.zombieinfection.updatechecker.UpdateChecker;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy/*, EntityDragon*/ {
	private boolean encrytionRegistered = false;
	private boolean updateEventRegistered = false;
	
	@Override public void init() {
		super.init();
		MinecraftForge.EVENT_BUS.register(new GuiEyeInfection(Minecraft.getMinecraft()));
		if (!updateEventRegistered) {
			MinecraftForge.EVENT_BUS.register(new InfectedPlayerRenderEvent());
			FMLCommonHandler.instance().bus().register(new UpdateChecker());
			updateEventRegistered = true;
		}
		if (!encrytionRegistered) MinecraftForge.EVENT_BUS.register(new InfectedPlayerTooltipEncryptEvent());
		encrytionRegistered = true;
	}
	
	@Override public void registerRenderers() {
		float shadowSize = 0.5F;
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieCow.class, new RenderZombieCow(new ModelCow(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieChicken.class, new RenderZombieChicken(new ModelChicken(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityZombiePig.class, new RenderZombiePig(new ModelPig(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieSheep.class, new RenderZombieSheep(new ModelZombieSheep(), shadowSize));
	}

	@Override public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == IdTracking.BOOK) return KnowledgeBook.instance;
		return null;
	}
}