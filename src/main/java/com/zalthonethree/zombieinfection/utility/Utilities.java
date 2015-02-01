package com.zalthonethree.zombieinfection.utility;

import net.minecraft.util.StatCollector;

public class Utilities {
	public static String Translate(String string) {
		return StatCollector.translateToLocal(string);
	}
	
	public static String TranslateFormatted(String string, Object... formatargs) {
		return StatCollector.translateToLocalFormatted(string, formatargs);
	}
}