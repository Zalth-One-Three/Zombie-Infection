package com.zalthonethree.zombieinfection.init;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.zalthonethree.zombieinfection.block.BlockDragonSpawner;
import com.zalthonethree.zombieinfection.block.BlockZendStone;

public class ModBlocks {
	public static final Block zendStone = new BlockZendStone();
	public static final Block dragonSpawner = new BlockDragonSpawner();
	
	public static void init() {
		GameRegistry.registerBlock(zendStone, "zendStone");
		GameRegistry.registerBlock(dragonSpawner, "dragonSpawner");
	}
}