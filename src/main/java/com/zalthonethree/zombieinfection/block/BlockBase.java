package com.zalthonethree.zombieinfection.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.client.CreativeTab;

public class BlockBase extends Block {
	
	public BlockBase(Material material) {
		super(material);
		this.setCreativeTab(CreativeTab.zombieInfection);
	}
	
	@Override public String getUnlocalizedName() {
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	public String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}