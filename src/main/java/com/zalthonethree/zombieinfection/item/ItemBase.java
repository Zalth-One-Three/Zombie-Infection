package com.zalthonethree.zombieinfection.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.client.CreativeTab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBase extends Item/*, EntityDragon*/ {
	public ItemBase() {
		super();
		setMaxStackSize(64);
		setCreativeTab(CreativeTab.zombieInfection);
		setNoRepair();
	}
	
	@Override public String getUnlocalizedName() {
		return String.format("item.%s%s", Reference.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override public String getUnlocalizedName(ItemStack itemStack) {
		return String.format("item.%s%s", Reference.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override @SideOnly(Side.CLIENT) public void registerIcons(IIconRegister iconRegister) {
		String BaseName = getUnwrappedUnlocalizedName(getUnlocalizedName());
		itemIcon = iconRegister.registerIcon(BaseName);
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}