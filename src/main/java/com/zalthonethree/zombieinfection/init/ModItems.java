package com.zalthonethree.zombieinfection.init;

import java.lang.reflect.Field;

import net.minecraftforge.fml.common.registry.GameRegistry;

import com.zalthonethree.zombieinfection.item.ItemBase;
import com.zalthonethree.zombieinfection.item.ItemCure;
import com.zalthonethree.zombieinfection.item.ItemNeedle;
import com.zalthonethree.zombieinfection.utility.LogHelper;

public class ModItems {
	public static final ItemBase CURE = new ItemCure();
	public static final ItemBase NEEDLE = new ItemNeedle();
	
	/*
	 * CODE CREATED BY: Six-One-Three
	 */
	public static void registerItems() throws IllegalArgumentException, IllegalAccessException {
		register_loop: for (Field field : ModItems.class.getFields()) {
			if (field.getType() == ItemBase.class) {
				ItemBase temp = (ItemBase) field.get(null);
				if (temp.getRegistryName() == null) {
					LogHelper.warn("Invalid Item: " + temp.getUnlocalizedName());
					continue register_loop;
				}
				GameRegistry.register(temp);
			}
		}
	}
	
	public static void init() {
		try {
			registerItems();
		} catch (Exception e) {}
	}
}