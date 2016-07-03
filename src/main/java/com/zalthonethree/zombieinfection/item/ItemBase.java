package com.zalthonethree.zombieinfection.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.init.CreativeTab;

public class ItemBase extends Item {
	public ItemBase() {
		super();
		this.setCreativeTab(CreativeTab.ZOMBIE_INFECTION);
		this.setNoRepair();
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