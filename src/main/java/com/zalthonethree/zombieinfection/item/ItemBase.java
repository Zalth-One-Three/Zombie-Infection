package com.zalthonethree.zombieinfection.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.client.CreativeTab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBase extends Item {
	public ItemBase() {
		super();
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTab.zombieInfection);
		this.setNoRepair();
	}
	
	@Override public String getUnlocalizedName() {
		return String.format("item.%s%s", Reference.RESOURCE_PREFIX, this.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override public String getUnlocalizedName(ItemStack itemStack) {
		return String.format("item.%s%s", Reference.RESOURCE_PREFIX, this.getUnwrappedUnlocalizedName(super.getUnlocalizedName(itemStack)));
	}
	
	@Override @SideOnly(Side.CLIENT) public void registerIcons(IIconRegister iconRegister) {
		String baseName = this.getUnwrappedUnlocalizedName(getUnlocalizedName());
		this.itemIcon = iconRegister.registerIcon(baseName);
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}