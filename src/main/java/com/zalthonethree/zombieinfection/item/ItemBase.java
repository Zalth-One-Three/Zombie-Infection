package com.zalthonethree.zombieinfection.item;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.client.CreativeTab;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemBase extends Item {
	public ItemBase() {
		super();
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTab.zombieInfection);
		this.setNoRepair();
	}
	
	public ItemBase setNames(String name) {
		this.setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
		this.setUnlocalizedName(name);
		return this;
	}
	
	@Override public String getUnlocalizedName() {
		return String.format("item.%s%s", Reference.RESOURCE_PREFIX, this.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override public String getUnlocalizedName(ItemStack itemStack) {
		return String.format("item.%s%s", Reference.RESOURCE_PREFIX, this.getUnwrappedUnlocalizedName(super.getUnlocalizedName(itemStack)));
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}