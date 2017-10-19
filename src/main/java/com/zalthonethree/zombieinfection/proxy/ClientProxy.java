package com.zalthonethree.zombieinfection.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import com.zalthonethree.zombieinfection.Reference;
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
import com.zalthonethree.zombieinfection.event.InfectedPlayerTooltipEncryptEvent;
import com.zalthonethree.zombieinfection.handler.ModelHelper;
import com.zalthonethree.zombieinfection.init.ModRegistry;

@Mod.EventBusSubscriber(Side.CLIENT) public class ClientProxy extends ServerProxy {
	@Override public void init() {
		super.init();
		MinecraftForge.EVENT_BUS.register(new GuiEyeInfection(Minecraft.getMinecraft()));
		MinecraftForge.EVENT_BUS.register(new InfectedPlayerTooltipEncryptEvent());
	}
	
	@SubscribeEvent public static void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(ModRegistry.NEEDLE, 0, new ModelResourceLocation(Reference.RESOURCE_PREFIX + "needle", "inventory"));
		ModelLoader.setCustomModelResourceLocation(ModRegistry.NEEDLE, 1, new ModelResourceLocation(Reference.RESOURCE_PREFIX + "needleblood", "inventory"));
		ModelLoader.setCustomModelResourceLocation(ModRegistry.NEEDLE, 2, new ModelResourceLocation(Reference.RESOURCE_PREFIX + "needleinfected", "inventory"));
		ModelLoader.setCustomModelResourceLocation(ModRegistry.NEEDLE, 3, new ModelResourceLocation(Reference.RESOURCE_PREFIX + "needlecured", "inventory"));
	}
	
	@Override public void registerRenderers() {
		/* Items */
		
		ModelHelper.registerItem(ModRegistry.CURE, new String[] {"cure"}, new int[] {0});
		ModelHelper.registerItem(ModRegistry.INFECTED_EGG);
		ModelHelper.registerItem(ModRegistry.INFECTED_MILK);
		ModelHelper.registerItem(ModRegistry.KNOWLEDGE_BOOK);
		
		/* Entities */
		
		float shadowSize = 0.5F;
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieCow.class, new RenderZombieCow(Minecraft.getMinecraft().getRenderManager(), new ModelCow(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieChicken.class, new RenderZombieChicken(Minecraft.getMinecraft().getRenderManager(), new ModelChicken(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityZombiePig.class, new RenderZombiePig(Minecraft.getMinecraft().getRenderManager(), new ModelPig(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieSheep.class, new RenderZombieSheep(Minecraft.getMinecraft().getRenderManager(), new ModelZombieSheep(), shadowSize));
	}

	@Override public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == IdTracking.BOOK) return KnowledgeBook.instance;
		return null;
	}
}