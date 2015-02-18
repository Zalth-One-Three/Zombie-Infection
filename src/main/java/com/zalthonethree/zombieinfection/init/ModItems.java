package com.zalthonethree.zombieinfection.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.zalthonethree.zombieinfection.item.ItemBase;
import com.zalthonethree.zombieinfection.item.ItemCure;
import com.zalthonethree.zombieinfection.item.ItemInfectedEgg;
import com.zalthonethree.zombieinfection.item.ItemInfectedMilk;
import com.zalthonethree.zombieinfection.utility.Utilities;

public class ModItems /*extends EntityDragon*/ {
	public static final ItemBase cure = new ItemCure();
	public static final ItemBase infectedEgg = new ItemInfectedEgg();
	public static final ItemBase infectedMilk = new ItemInfectedMilk();
	
	public static void init() {
		GameRegistry.registerItem(cure, "Cure");
		GameRegistry.registerItem(infectedEgg, "infectedEgg");
		GameRegistry.registerItem(infectedMilk, "infectedMilk");
		if (Utilities.isClientSide()) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(cure, 0, new ModelResourceLocation("zombieinfection:Cure", "inventory"));
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(cure, 0, new ModelResourceLocation("zombieinfection:infectedEgg", "inventory"));
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(cure, 0, new ModelResourceLocation("zombieinfection:infectedMilk", "inventory"));
		}
	}
}