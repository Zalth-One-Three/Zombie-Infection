package com.zalthonethree.zombieinfection.world;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public class GenLayerZend extends GenLayer {
	public GenLayerZend(long seed) {
		super(seed);
	}
	
	public static GenLayer[] makeTheWorld(long seed, WorldType type) {
		GenLayer biomes = new GenLayerBiomesZend(1L);
		for (long baseSeed = 1000; baseSeed <= 1005; baseSeed ++) {
			biomes = new GenLayerZoom(baseSeed, biomes);
		}
		GenLayer genLayerVoroniZoom = new GenLayerVoronoiZoom(10, biomes);
		biomes.initWorldGenSeed(seed);
		genLayerVoroniZoom.initWorldGenSeed(seed);
		return new GenLayer[] {biomes, genLayerVoroniZoom};
	}
	
	@Override public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
		return null;
	}
}
