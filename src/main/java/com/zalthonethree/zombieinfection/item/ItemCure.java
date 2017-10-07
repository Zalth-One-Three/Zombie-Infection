package com.zalthonethree.zombieinfection.item;

import java.util.List;

import javax.annotation.Nullable;

import com.zalthonethree.zombieinfection.api.ICustomCureEffect;
import com.zalthonethree.zombieinfection.api.ZombieInfectionAPI;
import com.zalthonethree.zombieinfection.init.ModRegistry;
import com.zalthonethree.zombieinfection.potion.PotionHelper;
import com.zalthonethree.zombieinfection.utility.Utilities;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCure extends ItemBase {
	public ItemCure() {
		super();
		this.setFull3D();
		this.setNames("cure");
	}
	
	@Override public boolean hasEffect(ItemStack stack) { return true; }
	
	@Override @SideOnly(Side.CLIENT) public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.GOLD + Utilities.translate("tooltip.cure"));
	}
	
	@Override public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if (!(entityLiving instanceof EntityPlayer)) return stack;
		EntityPlayer player = (EntityPlayer) entityLiving;
		player.addPotionEffect(PotionHelper.createCure(0));
		for (ICustomCureEffect customEffect : ZombieInfectionAPI.getCustomCureEffects()) {
			customEffect.run(player, stack);
		}
		stack.setCount(player.capabilities.isCreativeMode ? stack.getCount() : stack.getCount() - 1);
		if (stack.getCount() == 0) {
			stack = new ItemStack(Items.GLASS_BOTTLE);
		} else {
			boolean increased = false;
			for (int i = 0; i < player.inventory.getSizeInventory(); i ++) {
				if (player.inventory.getStackInSlot(i) != null) {
					if (player.inventory.getStackInSlot(i).getUnlocalizedName().equalsIgnoreCase("item.glassBottle")) {
						if (player.inventory.getStackInSlot(i).getCount() < 64) {
							player.inventory.getStackInSlot(i).grow(1);
							increased = true;
							break;
						}
					}
				}
			}
			if (!increased) {
			int emptySlotPos = player.inventory.getFirstEmptyStack();
				if (emptySlotPos > -1) {
					player.inventory.setInventorySlotContents(emptySlotPos, new ItemStack(Items.GLASS_BOTTLE));
				} else {
					player.dropItem(new ItemStack(Items.GLASS_BOTTLE), false);
				}
			}
		}
		return stack;
	}
	
	@Override public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack stack = playerIn.getHeldItem(hand);
		if (playerIn.isPotionActive(ModRegistry.POTION_INFECTION) && !playerIn.isPotionActive(ModRegistry.POTION_CURE)) playerIn.setActiveHand(hand);
		return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
	}
	
	@Override public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
	
	@Override public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}
}