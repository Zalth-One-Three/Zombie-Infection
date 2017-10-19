package com.zalthonethree.zombieinfection.handler;

import java.util.ArrayList;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.utility.LogHelper;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT) public class ModelHelper {
	public static void registerItemInternal(Item item, String[] registryNames, int[] registryMetas) {
		if (registryNames.length != registryMetas.length) {
			LogHelper.warn("Could not register models for " + item.getUnlocalizedName() + ": registryNames and registryMetas do not have matching lengths.");
			return;
		}
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		for (int i = 0; i < registryNames.length; i ++) {
			mesher.register(item, registryMetas[i], new ModelResourceLocation(Reference.RESOURCE_PREFIX + registryNames[i], "inventory"));
		}
	}
	
	public static void registerItem(Item item, String[] registryNames, int[] registryMetas) {
		ModelBakery.registerItemVariants(item, generateVariants(registryNames));
		registerItemInternal(item, registryNames, registryMetas);
	}
	
	public static void registerBlock(Block block, IStateMapper mapper, String[] registryNames, int[] registryMetas) {
		registerItemInternal(Item.getItemFromBlock(block), registryNames, registryMetas);
		ModelBakery.registerItemVariants(Item.getItemFromBlock(block), generateVariants(registryNames));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getBlockModelShapes().registerBlockWithStateMapper(block, mapper);
	}
	
	public static void registerBlock(Block block, String[] registryNames, int[] registryMetas) {
		registerBlock(block, null, registryNames, registryMetas);
	}
	
	public static void registerBlock(Block block, String registryName, IStateMapper mapper) {
		registerBlock(block, mapper, new String[] {registryName}, new int[] {0});
	}
	
	public static void registerBlock(Block block, String registryName) {
		registerBlock(block, null, new String[] {registryName}, new int[] {0});
	}
	
	public static void registerBlock(Block block) {
		registerBlock(block, null, new String[] {block.getUnlocalizedName().substring(5).replace(Reference.RESOURCE_PREFIX, "")}, new int[] {0});
	}
	
	public static void registerItem(Item item, String registryName) {
		registerItemInternal(item, new String[] {registryName}, new int[] {0});
	}
	
	public static void registerItem(Item item, int[] registryMetas) {
		String[] registryNames = new String[registryMetas.length];
		for (int i = 0; i < registryNames.length; i ++) {
			registryNames[i] = item.getUnlocalizedName().substring(5).replace(Reference.RESOURCE_PREFIX, "");
		}
		ModelBakery.registerItemVariants(item, generateVariants(registryNames));
		registerItemInternal(item, registryNames, registryMetas);
	}
	
	public static void registerItem(Item item) {
		registerItemInternal(item, new String[] {item.getUnlocalizedName().substring(5).replace(Reference.RESOURCE_PREFIX, "")}, new int[] {0});
	}
	
	public static void removeBlockState(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getBlockModelShapes().registerBuiltInBlocks(block);
	}
	
	static ResourceLocation[] generateVariants(String[] registryNames) {
		ArrayList<ResourceLocation> ret = new ArrayList<ResourceLocation>();
		for (String aString : registryNames) {
			ret.add(new ResourceLocation(Reference.MOD_ID, aString.contains(Reference.RESOURCE_PREFIX) ? aString.replace(Reference.RESOURCE_PREFIX, "") : aString));
		}
		return ret.toArray(new ResourceLocation[] {});
	}
}