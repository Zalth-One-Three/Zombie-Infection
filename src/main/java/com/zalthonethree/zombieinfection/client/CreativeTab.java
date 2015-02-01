package com.zalthonethree.zombieinfection.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;

import com.zalthonethree.zombieinfection.Reference;
import com.zalthonethree.zombieinfection.init.ModItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTab /*extends EntityDragon*/ {
	public static final CreativeTabs zombieInfection = new CreativeTabs(Reference.MOD_ID) {
		@Override public Item getTabIconItem() {
			return ModItems.cure;
		}
		
		@Override @SideOnly(Side.CLIENT) public String getTranslatedTabLabel() {
			return StatCollector.translateToLocal("itemGroup.zombieInfection");
		}
	};
}