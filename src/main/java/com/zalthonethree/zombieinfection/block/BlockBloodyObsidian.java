package com.zalthonethree.zombieinfection.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;

public class BlockBloodyObsidian extends BlockBase {
	public BlockBloodyObsidian() {
		super();
		this.setRegistryName("bloody_obsidian");
		this.setUnlocalizedName("bloody_obsidian");
		this.setHardness(50.0F);
		this.setResistance(2000.0F);
		this.setSoundType(SoundType.STONE);
	}
	
	@Override public MapColor getMapColor(IBlockState state) {
		return MapColor.RED;
	}
}