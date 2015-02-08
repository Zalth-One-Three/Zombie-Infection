package com.zalthonethree.zombieinfection.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.zalthonethree.zombieinfection.api.CustomCureEffect;
import com.zalthonethree.zombieinfection.api.ZombieInfectionAPI;
import com.zalthonethree.zombieinfection.potion.PotionHelper;
import com.zalthonethree.zombieinfection.utility.Utilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCure extends ItemBase/*, EntityDragon*/ {
	public ItemCure() {
		super();
		setUnlocalizedName("cure");
		setFull3D();
	}
	
	@Override public boolean hasEffect(ItemStack stack) { return true; }
	
	@SuppressWarnings({"rawtypes", "unchecked"}) @SideOnly(Side.CLIENT) public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
		list.add(EnumChatFormatting.GOLD + Utilities.Translate("tooltip.cure"));
	}
	
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityPlayer player) {
		player.addPotionEffect(PotionHelper.createCure(0));
		for (CustomCureEffect customEffect : ZombieInfectionAPI.getCustomCureEffects()) {
			customEffect.run(player, stack);
		}
		stack.stackSize = player.capabilities.isCreativeMode ? stack.stackSize : stack.stackSize - 1;
		return stack;
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.setItemInUse(stack, getMaxItemUseDuration(stack));
		return stack;
	}
	
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
	
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.drink;
	}
}