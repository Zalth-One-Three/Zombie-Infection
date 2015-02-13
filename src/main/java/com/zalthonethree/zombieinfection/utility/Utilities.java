package com.zalthonethree.zombieinfection.utility;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.util.StatCollector;

public class Utilities /*extends EntityDragon*/ {
	public static String Translate(String string) {
		return StatCollector.translateToLocal(string);
	}
	
	public static String TranslateFormatted(String string, Object... formatargs) {
		return StatCollector.translateToLocalFormatted(string, formatargs);
	}
	
	public static boolean isServerSide() { return FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER; }
	public static boolean isClientSide() { return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT; }
}