package com.zalthonethree.zombieinfection.utility;

import net.minecraft.util.StatCollector;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class Utilities {
	public static String Translate(String string) {
		return StatCollector.translateToLocal(string);
	}
	
	public static String TranslateFormatted(String string, Object... formatargs) {
		return StatCollector.translateToLocalFormatted(string, formatargs);
	}
	
	public static boolean isServerSide() { return FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER; }
	public static boolean isClientSide() { return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT; }
}