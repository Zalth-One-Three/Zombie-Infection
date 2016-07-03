package com.zalthonethree.zombieinfection.proxy;

import com.zalthonethree.zombieinfection.handler.ModelHelper;
import com.zalthonethree.zombieinfection.init.ModItems;

public class ClientProxy extends ServerProxy {
	@Override public void init() {
		super.init();
		this.registerRenderers();
	}
	
	public void registerRenderers() {
		/* Items */
		
		ModelHelper.registerItem(ModItems.CURE, new String[] {"cure"}, new int[] {0});
		ModelHelper.registerItem(ModItems.NEEDLE, new String[] {"needle", "needleBlood", "needleInfected", "needleCured"}, new int[] {0, 1, 2, 3});
	}
}