package com.zalthonethree.zombieinfection.utility;

import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

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