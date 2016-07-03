package com.zalthonethree.zombieinfection.init;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.zalthonethree.zombieinfection.Reference;

public class CreativeTab {
	public static final CreativeTabs ZOMBIE_INFECTION = new CreativeTabs(Reference.MOD_ID) {
		@Override @SideOnly(Side.CLIENT) public Item getTabIconItem() {
			return Items.ROTTEN_FLESH; // TODO Change this.
		}
		
		@Override @SideOnly(Side.CLIENT) public String getTranslatedTabLabel() {
			return I18n.format("itemGroup.zombieInfection");
		}
	};
}