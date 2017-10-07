package com.zalthonethree.zombieinfection.item;

import com.zalthonethree.zombieinfection.init.ModRegistry;
import com.zalthonethree.zombieinfection.utility.CustomDamageSource;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ItemNeedle extends ItemBase {
	/* META REFERENCE
	 * 0 - Empty Needle
	 * 1 - Needle, Regular Blood
	 * 2 - Needle, Infected Blood
	 * 3 - Needle, Cured Blood
	 */
	
	public static final String[] names = new String[] {"", "Normal", "Infected", "Cured", "Unknown"};
	
	public ItemNeedle() {
		super();
		this.setNames("needle");
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
	}
	
	@Override public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack itemStackIn = playerIn.getHeldItem(hand);
		if (itemStackIn.getItemDamage() > 0) return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
		if (playerIn.isPotionActive(ModRegistry.POTION_CURE)) {
			itemStackIn.setItemDamage(3);
		} else if (playerIn.isPotionActive(ModRegistry.POTION_INFECTION)) {
			itemStackIn.setItemDamage(2);
		} else {
			itemStackIn.setItemDamage(1);
		}
		if (!worldIn.isRemote) playerIn.attackEntityFrom(new CustomDamageSource("needle", "zombieinfection.death.needle"), 5.0F);
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	}
	
	@Override public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if (target instanceof EntityZombie && stack.getItemDamage() == 0) {
			stack.setItemDamage(2);
			return true;
		}
		return false;
	}
	
	@Override public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (!this.isInCreativeTab(tab)) return;
		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
		subItems.add(new ItemStack(this, 1, 2));
		subItems.add(new ItemStack(this, 1, 3));
	}
	
	@Override public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + (stack.getItemDamage() < names.length ? names[stack.getItemDamage()] : names[4]);
	}
}