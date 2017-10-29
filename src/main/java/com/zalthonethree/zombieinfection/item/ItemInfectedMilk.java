package com.zalthonethree.zombieinfection.item;

import com.zalthonethree.zombieinfection.potion.PotionHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemInfectedMilk extends ItemBase {
	public ItemInfectedMilk() {
		super();
		this.setNames("infectedmilk");
		this.setMaxStackSize(1);
	}
	
	@Override public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if (!player.capabilities.isCreativeMode) player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("nausea"), 100));
		}
	}
	
	@Override public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
	
	@Override public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}
	
	//TODO, I dunno, what should happen if someone drinks infected milk.
	@Override public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (!(entityLiving instanceof EntityPlayer)) return stack;
		EntityPlayer player = (EntityPlayer) entityLiving;
		
		if (!player.capabilities.isCreativeMode) {
			player.addPotionEffect(PotionHelper.createInfection(0));
			stack.shrink(1);
		}
		
		return stack.getCount() <= 0 ? new ItemStack(Items.BUCKET) : stack;
	}
	
	@Override public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.setActiveHand(hand);
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(hand));
	}
}