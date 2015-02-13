package com.zalthonethree.zombieinfection.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes /*extends EntityDragon*/ {
	public static void init() {
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.cure, 3, 0), "www", "sbs", "bgb", 'w', new ItemStack(Items.wheat), 's', new ItemStack(Items.sugar), 'b', new ItemStack(Items.potionitem), 'g', new ItemStack(Items.gold_ingot));
	}
}