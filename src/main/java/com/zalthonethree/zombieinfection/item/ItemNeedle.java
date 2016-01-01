package com.zalthonethree.zombieinfection.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.zalthonethree.zombieinfection.potion.ModPotion;
import com.zalthonethree.zombieinfection.utility.CustomDamageSource;

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
		this.setUnlocalizedName("needle");
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
	}
	
	@Override public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (stack.getItemDamage() > 0) return stack;
		if (player.isPotionActive(ModPotion.potionCure)) {
			stack.setItemDamage(3);
		} else if (player.isPotionActive(ModPotion.potionInfection)) {
			stack.setItemDamage(2);
		} else {
			stack.setItemDamage(1);
		}
		if (!world.isRemote) player.attackEntityFrom(new CustomDamageSource("needle", "zombieinfection.death.needle"), 5.0F);
		return stack;
	}
	
	@Override public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase entity) {
		if (entity instanceof EntityZombie && stack.getItemDamage() == 0) {
			stack.setItemDamage(2);
			return true;
		}
		return false;
	}
	
	@Override @SideOnly(Side.CLIENT) public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> items) {
		items.add(new ItemStack(item, 1, 0));
		items.add(new ItemStack(item, 1, 1));
		items.add(new ItemStack(item, 1, 2));
		items.add(new ItemStack(item, 1, 3));
	}
	
	@Override public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + (stack.getItemDamage() < names.length ? names[stack.getItemDamage()] : names[4]);
	}
}