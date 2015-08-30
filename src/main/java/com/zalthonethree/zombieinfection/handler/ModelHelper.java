package com.zalthonethree.zombieinfection.handler;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT) public class ModelHelper {
	public static void registerItem(Item item, int... metadata) {
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		for (int i : metadata) {
			mesher.register(item, i, new ModelResourceLocation(item.getUnlocalizedName().substring(5), "inventory"));
		}
	}
	
	public static void registerBlock(Block block, int metadata) {
		registerItem(Item.getItemFromBlock(block), metadata);
	}
	
	public static void registerBlock(Block block) {
		registerBlock(block, 0);
	}
	
	public static void registerItem(Item item) {
		registerItem(item, 0);
	}
	
	public static void removeblockstate(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getBlockModelShapes().registerBuiltInBlocks(block);
	}
}