package com.zalthonethree.zombieinfection.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.zalthonethree.zombieinfection.Reference.IdTracking;
import com.zalthonethree.zombieinfection.ZombieInfection;

public class ItemKnowledgeBook extends ItemBase {
	public ItemKnowledgeBook() {
		super();
		setUnlocalizedName("knowledgeBook");
	}
	
	@Override public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
		player.openGui(ZombieInfection.instance, IdTracking.BOOK, worldIn, 0, 0, 0);
		return itemStackIn;
	}
	
	@Override public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		tooltip.add(StatCollector.translateToLocal("tooltip.knowledgeBook"));
	}
}