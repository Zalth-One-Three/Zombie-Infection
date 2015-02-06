package com.zalthonethree.zombieinfection.entity;

import java.util.Random;

import com.zalthonethree.zombieinfection.init.ModItems;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class MedicVillagerTradeHandler implements IVillageTradeHandler {
	@SuppressWarnings("unchecked") @Override public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
		recipeList.clear();
		recipeList.add(new MerchantRecipe(new ItemStack(Items.gold_ingot), new ItemStack(ModItems.cure, 3)));
	}
}