package com.zalthonethree.zombieinfection.init;

import java.lang.reflect.Field;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.zalthonethree.zombieinfection.block.BlockBase;
import com.zalthonethree.zombieinfection.block.BlockBloodyObsidian;
import com.zalthonethree.zombieinfection.utility.LogHelper;

public class ModBlocks {
	public static final BlockBase BLOODY_OBSIDIAN = new BlockBloodyObsidian();
	
	/*
	 * CODE CREATED BY: Six-One-Three
	 */
	public static void registerBlocks() throws IllegalArgumentException, IllegalAccessException {
		register_loop: for (Field field : ModBlocks.class.getFields()) {
			if (field.getType() == BlockBase.class) {
				BlockBase temp = (BlockBase) field.get(null);
				if (temp.getRegistryName() == null) {
					LogHelper.warn("Invalid Block: " + temp.getUnlocalizedName());
					continue register_loop;
				}
				GameRegistry.register(temp);
				if (temp.hasOwnItemBlock()) {
					ItemBlock temp_IB = temp.getOwnItemBlock();
					if (temp_IB.getRegistryName() == null) {
						temp_IB.setRegistryName(temp_IB.block.getRegistryName());
					}
					GameRegistry.register(temp_IB);
				} else {
					ItemBlock temp_IB = new ItemBlock(temp);
					temp_IB.setRegistryName(temp.getRegistryName());
					GameRegistry.register(temp_IB);
				}
			}
		}
	}
	
	public static void init() {
		try {
			registerBlocks();
		} catch (Exception e) {}
	}
}