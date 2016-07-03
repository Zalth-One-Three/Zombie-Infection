package com.zalthonethree.zombieinfection.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.init.CreativeTab;

public class BlockBase extends Block {
	
	/* Fields */
	
	@Nullable private ItemBlock ownItemBlock = null;
	
	/* Constructors */
	
	public BlockBase(Material material) {
		super(material);
		this.setCreativeTab(CreativeTab.ZOMBIE_INFECTION);
	}
	
	public BlockBase() {
		this(Material.ROCK);
	}
	
	/* Custom Methods */
	
	public String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
	
	public boolean hasOwnItemBlock() {
		return this.ownItemBlock != null;
	}
	
	public ItemBlock getOwnItemBlock() {
		return this.ownItemBlock;
	}
	
	public BlockBase setOwnItemBlock(ItemBlock itemBlock) {
		this.ownItemBlock = itemBlock;
		return this;
	}
	
	/* Overridden */
	
	@Override public String getUnlocalizedName() {
		return String.format("tile.%s%s", Reference.RESOURCE_PREFIX, this.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
}