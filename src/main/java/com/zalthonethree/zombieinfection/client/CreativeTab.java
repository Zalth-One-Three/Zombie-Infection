package com.zalthonethree.zombieinfection.client;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.init.ModRegistry;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTab {
	public static final ItemStack ICON = new ItemStack(ModRegistry.CURE);
	
	public static final CreativeTabs zombieInfection = new CreativeTabs(Reference.MOD_ID) {
		@Override @SideOnly(Side.CLIENT) public ItemStack getTabIconItem() {
			return ICON;
		}
		
		@Override @SideOnly(Side.CLIENT) public String getTranslatedTabLabel() {
			return I18n.format("itemGroup.zombieInfection");
		}
	};
}