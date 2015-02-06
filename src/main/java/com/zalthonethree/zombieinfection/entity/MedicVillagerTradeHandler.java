package com.zalthonethree.zombieinfection.entity;

import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

import com.zalthonethree.zombieinfection.init.ModItems;

public class MedicVillagerTradeHandler implements EntityVillager.ITradeList {
	@SuppressWarnings("unchecked") @Override public void modifyMerchantRecipeList(MerchantRecipeList recipeList, Random random) {
		recipeList.clear();
		recipeList.add(new MerchantRecipe(new ItemStack(Items.gold_ingot), new ItemStack(ModItems.cure, 3)));
	}
}