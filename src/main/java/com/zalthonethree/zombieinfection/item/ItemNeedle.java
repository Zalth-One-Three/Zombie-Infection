package com.zalthonethree.zombieinfection.item;

public class ItemNeedle extends ItemBase {
	/* META REFERENCE
	 * 0 - Empty Needle
	 * 1 - Needle, Regular Blood
	 * 2 - Needle, Infected Blood
	 * 3 - Needle, Cured Blood
	 */
	public ItemNeedle() {
		super();
		this.setUnlocalizedName("needle");
		this.setMaxStackSize(1);
	}
}