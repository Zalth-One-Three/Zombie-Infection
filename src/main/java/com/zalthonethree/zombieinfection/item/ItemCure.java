package com.zalthonethree.zombieinfection.item;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.zalthonethree.zombieinfection.utility.Utilities;

public class ItemCure extends ItemBase {
	public ItemCure() {
		super();
		this.setRegistryName("cure");
		this.setUnlocalizedName("cure");
		this.setMaxStackSize(1);
	}
	
	@Override @SideOnly(Side.CLIENT) public boolean hasEffect(ItemStack stack) {
		return true;
	}
	
	@Override @SideOnly(Side.CLIENT) public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced) {
		list.add(TextFormatting.GOLD + Utilities.Translate("tooltip.cure"));
	}
	
	@Override public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
/*		if (!(entityLiving instanceof EntityPlayer)) return stack;
		EntityPlayer player = (EntityPlayer) entityLiving;
		player.addPotionEffect(PotionHelper.createCure(0));
		for (ICustomCureEffect customEffect : ZombieInfectionAPI.getCustomCureEffects()) {
			customEffect.run(player, stack);
		}
		stack.stackSize = player.capabilities.isCreativeMode ? stack.stackSize : stack.stackSize - 1;
		if (stack.stackSize == 0) {
			stack = new ItemStack(Items.glass_bottle);
		} else {
			boolean increased = false;
			for (int i = 0; i < player.inventory.getSizeInventory(); i ++) {
				if (player.inventory.getStackInSlot(i) != null) {
					if (player.inventory.getStackInSlot(i).getUnlocalizedName().equalsIgnoreCase("item.glassBottle")) {
						if (player.inventory.getStackInSlot(i).stackSize < 64) {
							player.inventory.getStackInSlot(i).stackSize ++;
							increased = true;
							break;
						}
					}
				}
			}
			if (!increased) {
			int emptySlotPos = player.inventory.getFirstEmptyStack();
				if (emptySlotPos > -1) {
					player.inventory.setInventorySlotContents(emptySlotPos, new ItemStack(Items.glass_bottle));
				} else {
					player.dropPlayerItemWithRandomChoice(new ItemStack(Items.glass_bottle), false);
				}
			}
		}*/
		return stack;
	}
	
	@Override public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		// if (playerIn.isPotionActive(ModPotion.potionInfection) && !playerIn.isPotionActive(ModPotion.potionCure)) playerIn.setActiveHand(hand);
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	}
	
	@Override public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
	
	@Override public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}
}