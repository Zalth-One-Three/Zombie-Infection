package com.zalthonethree.zombieinfection.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.zalthonethree.zombieinfection.item.ItemBase;
import com.zalthonethree.zombieinfection.item.ItemCure;
import com.zalthonethree.zombieinfection.utility.Utilities;

public class ModItems /*extends EntityDragon*/ {
	public static final ItemBase cure = new ItemCure();
	
	public static void init() {
		GameRegistry.registerItem(cure, "Cure");
		if (Utilities.isClientSide()) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(cure, 0, new ModelResourceLocation("zombieinfection:Cure", "inventory"));
		}
	}
}