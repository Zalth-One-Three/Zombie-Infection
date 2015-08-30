package com.zalthonethree.zombieinfection.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import com.zalthonethree.zombieinfection.handler.ConfigurationHandler;
import com.zalthonethree.zombieinfection.utility.SpecialTeleporter;

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
		return EnumAction.DRINK;
	}
	
	@Override public ItemStack onItemUseFinish(ItemStack stack, World world, EntityPlayer player) {
		//TODO, I dunno, what should happen if someone drinks infected milk.
		
		// DEBUG CODE
		if (!world.isRemote) {
			if (player instanceof EntityPlayerMP) {
				int dimId = ConfigurationHandler.getZendDimensionId();
				EntityPlayerMP playerMP = (EntityPlayerMP) player;
				WorldServer ws = playerMP.mcServer.worldServerForDimension(dimId);
				Teleporter teleporter = new SpecialTeleporter(ws);
				
				if (!(player.dimension == dimId) && player.ridingEntity == null) {
					playerMP.setPositionAndUpdate(0, 256, 0);
					playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, dimId, teleporter);
					SpecialTeleporter.adjustPosY(player);
				} else if (player.dimension == dimId && player.ridingEntity == null) {
					playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, 0, teleporter);
					SpecialTeleporter.adjustPosY(player);
				}
			}
		}
		// END DEBUG CODE
		
		if (!player.capabilities.isCreativeMode) {
			stack.stackSize --;
		}
		
		return stack.stackSize <= 0 ? new ItemStack(Items.bucket) : stack;
	}
	
	@Override public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
		player.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
		return itemStackIn;
	}
}