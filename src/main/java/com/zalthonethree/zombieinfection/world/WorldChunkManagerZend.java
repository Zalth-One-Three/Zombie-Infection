package com.zalthonethree.zombieinfection.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.zalthonethree.zombieinfection.ZombieInfection;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldChunkManagerZend extends WorldChunkManager {
	private GenLayer genBiomes;
	private GenLayer biomeIndexLayer;
	private BiomeCache biomeCache;
	private List<BiomeGenBase> biomesToSpawnIn;
	
	@SuppressWarnings({"rawtypes", "unchecked"}) public WorldChunkManagerZend() {
		this.biomeCache = new BiomeCache(this);
		this.biomesToSpawnIn = new ArrayList();
		this.biomesToSpawnIn.addAll(allowedBiomes);
	}
	
	public WorldChunkManagerZend(long seed, WorldType worldType) {
		this();
		
		GenLayer[] genLayer = GenLayerZend.makeTheWorld(seed, worldType);
		genLayer = getModdedBiomeGenerators(worldType, seed, genLayer);
		this.genBiomes = genLayer[0];
		this.biomeIndexLayer = genLayer[1];
	}
	
	public WorldChunkManagerZend(World world) {
		this(world.getSeed(), world.getWorldInfo().getTerrainType());
	}
	
	@Override public List<BiomeGenBase> getBiomesToSpawnIn() {
		return this.biomesToSpawnIn;
	}
	
	@Override public BiomeGenBase getBiomeGenerator(BlockPos pos) {
		return this.biomeCache.func_180284_a(pos.getX(), pos.getZ(), ZombieInfection.biomeZend);
	}
	
	@Override public float[] getRainfall(float[] listToReuse, int x, int z, int width, int length) {
		if (listToReuse == null || listToReuse.length < width * length) {
			listToReuse = new float[width * length];
		}
		
		Arrays.fill(listToReuse, 0, width * length, 0);
		return listToReuse;
	}
	
	@Override @SideOnly(Side.CLIENT) public float getTemperatureAtHeight(float originalTemp, int height) {
		return originalTemp;
	}
	
	@Override public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] biomes, int par2, int par3, int width, int length) {
		if (biomes == null || biomes.length < width * length) {
			biomes = new BiomeGenBase[width * length];
		}
		
		Arrays.fill(biomes, 0, width * length, ZombieInfection.biomeZend);
		return biomes;
	}
	
	@Override public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] oldBiomeList, int x, int z, int width, int depth) {
		if (oldBiomeList == null || oldBiomeList.length < width * depth) {
			oldBiomeList = new BiomeGenBase[width * depth];
		}
		
		Arrays.fill(oldBiomeList, 0, width * depth, ZombieInfection.biomeZend);
		return oldBiomeList;
	}
	
	@Override public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] listToReuse, int x, int z, int width, int length, boolean cacheFlag) {
		return this.loadBlockGeneratorData(listToReuse, x, z, width, length);
	}
	
	@SuppressWarnings("rawtypes") @Override public BlockPos findBiomePosition(int x, int z, int range, List biomes, Random random) {
		return biomes.contains(ZombieInfection.biomeZend) ? new BlockPos(x - range + random.nextInt(range * 2 + 1), 0, z - range + random.nextInt(range * 2 + 1)) : null;
	}
	
	@SuppressWarnings("rawtypes") @Override public boolean areBiomesViable(int p_76940_1_, int p_76940_2_, int p_76940_3_, List p_76940_4_) {
		return p_76940_4_.contains(ZombieInfection.biomeZend);
	}
}