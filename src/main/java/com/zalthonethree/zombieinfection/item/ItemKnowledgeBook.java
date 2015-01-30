package com.zalthonethree.zombieinfection.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

import com.zalthonethree.zombieinfection.Reference.IdTracking;
import com.zalthonethree.zombieinfection.ZombieInfection;

public class ItemKnowledgeBook extends ItemBase {
	public ItemKnowledgeBook() {
		super();
		setUnlocalizedName("knowledgeBook");
	}
	
	@Override public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.openGui(ZombieInfection.instance, IdTracking.BOOK, worldIn, 0, 0, 0);
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	}
	
	@Override public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		tooltip.add(I18n.translateToLocal("tooltip.knowledgeBook"));
	}
}