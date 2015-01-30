package com.zalthonethree.zombieinfection.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.init.ModItems;

public class CreativeTab {
	public static final CreativeTabs zombieInfection = new CreativeTabs(Reference.MOD_ID) {
		@Override public Item getTabIconItem() {
			return ModItems.cure;
		}
		
		@Override @SideOnly(Side.CLIENT) public String getTranslatedTabLabel() {
			return I18n.translateToLocal("itemGroup.zombieInfection");
		}
	};
}