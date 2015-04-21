package com.zalthonethree.zombieinfection.item;

import java.util.List;

import com.zalthonethree.zombieinfection.ZombieInfection;
import com.zalthonethree.zombieinfection.Reference.IdTracking;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemKnowledgeBook extends ItemBase {
	public ItemKnowledgeBook() {
		super();
		setUnlocalizedName("knowledgeBook");
	}
	
	@Override public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
		player.openGui(ZombieInfection.instance, IdTracking.BOOK, worldIn, 0, 0, 0);
		return itemStackIn;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"}) @Override public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		tooltip.add(StatCollector.translateToLocal("tooltip.knowledgeBook"));
	}
}