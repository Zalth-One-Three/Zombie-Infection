package com.zalthonethree.zombieinfection.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
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
	
	@Override public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			player.addPotionEffect(new PotionEffect(Potion.confusion.id, 20));
		}
	}
}