package com.zalthonethree.zombieinfection.world;

import com.zalthonethree.zombieinfection.ZombieInfection;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomesZend extends GenLayer {
	protected BiomeGenBase[] allowedBiomes = {ZombieInfection.biomeZend};
	
	public GenLayerBiomesZend(long seed) {
		super(seed);
	}
	
	public GenLayerBiomesZend(long seed, GenLayer genlayer) {
		super(seed);
		this.parent = genlayer;
	}
	
	@Override public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
		int[] dest = IntCache.getIntCache(areaWidth * areaHeight);
		for (int dz = 0; dz < areaHeight; dz ++) {
			for (int dx = 0; dx < areaWidth; dx ++) {
				this.initChunkSeed(dx + areaX, dz + areaY);
				dest[(dx + dz * areaWidth)] = this.allowedBiomes[nextInt(this.allowedBiomes.length)].biomeID;
			}
		}
		return dest;
	}
}
