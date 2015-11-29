package com.zalthonethree.zombieinfection.potion;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionCure extends Potion {
	public PotionCure(int id, ResourceLocation res_loc, boolean bad, int color) {
		super(id, res_loc, bad, color);
	}
	
	@Override public Potion setIconIndex(int par1, int par2) {
		super.setIconIndex(par1, par2);
		return this;
	}
}