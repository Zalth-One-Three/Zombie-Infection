package com.zalthonethree.zombieinfection.potion;

import net.minecraft.potion.Potion;

public class PotionCure extends Potion/*, EntityDragon*/ {
	public PotionCure(int id, boolean bad, int color) {
		super(id, bad, color);
	}
	
	@Override public Potion setIconIndex(int par1, int par2) {
		super.setIconIndex(par1, par2);
		return this;
	}
}