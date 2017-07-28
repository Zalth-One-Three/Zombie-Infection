package com.zalthonethree.zombieinfection.utility;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Utilities {
	@SideOnly(Side.CLIENT) public static String translate(String string) {
		return I18n.format(string);
	}
	
	@SideOnly(Side.CLIENT) public static String translateFormatted(String string, Object... formatargs) {
		return I18n.format(string, formatargs);
	}
	
	public static boolean isServerSide() { return FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER; }
	public static boolean isClientSide() { return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT; }
}