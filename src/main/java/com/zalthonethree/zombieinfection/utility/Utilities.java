package com.zalthonethree.zombieinfection.utility;

import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class Utilities {
	public static String Translate(String string) {
		return I18n.translateToLocal(string);
	}
	
	public static String TranslateFormatted(String string, Object... formatargs) {
		return I18n.translateToLocalFormatted(string, formatargs);
	}
	
	public static boolean isServerSide() { return FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER; }
	public static boolean isClientSide() { return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT; }
}