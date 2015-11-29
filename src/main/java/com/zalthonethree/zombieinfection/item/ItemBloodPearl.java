package com.zalthonethree.zombieinfection.item;

public class ItemBloodPearl extends ItemBase {
	/* META REFERENCE
	 * 0 - Bloody Ender Pearl
	 * 		Combine a Needle with Regular Blood with an Ender Pearl
	 * 1 - Infectios Ender Pearl
	 * 		Combine a Needle with Infected Blood with an Ender Pearl
	 * 2 - Curatious Ender Pearl
	 * 		Combine a Needle with Cured Blood with an Ender Pearl
	 */
	public ItemBloodPearl() {
		super();
		this.setUnlocalizedName("bloodPearl");
		this.setMaxStackSize(16);
	}
}
