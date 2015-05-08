package com.zalthonethree.zombieinfection.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemInfectedMilk extends ItemBase/*, EntityDragon*/ {
	public ItemInfectedMilk() {
		super();
		setUnlocalizedName("infectedMilk");
		setMaxStackSize(1);
	}
	
	@Override public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if (!player.capabilities.isCreativeMode) player.addPotionEffect(new PotionEffect(Potion.confusion.id, 100));
		}
	}
	
	@Override public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
	
	@Override public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.drink;
	}
	
	@Override public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		//TODO, I dunno, what should happen if someone drinks infected milk.
		if (!playerIn.capabilities.isCreativeMode) {
			stack.stackSize --;
		}
		
		return stack.stackSize <= 0 ? new ItemStack(Items.bucket) : stack;
	}
	
	@Override public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
		player.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
		return itemStackIn;
	}
}