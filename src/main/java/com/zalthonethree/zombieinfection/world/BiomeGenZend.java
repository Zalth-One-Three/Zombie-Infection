package com.zalthonethree.zombieinfection.world;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

import com.zalthonethree.zombieinfection.entity.zend.EntityEnderZombie;
import com.zalthonethree.zombieinfection.init.ModBlocks;

public class BiomeGenZend extends BiomeGenBase {
	public BiomeGenZend(int id) {
		super(id);
		
		this.enableRain = false;
		this.enableSnow = false;
		
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.flowers.clear();
		
		this.theBiomeDecorator = new BiomeDecorator() {
			@Override public void decorate(World worldIn, Random rand, BiomeGenBase biome, BlockPos pos) {}
		};
		
		this.topBlock = ModBlocks.zendStone.getDefaultState();
		this.fillerBlock = ModBlocks.zendStone.getDefaultState();
		this.setHeight(height_Default);
		this.setBiomeName("Zend");
		
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityEnderZombie.class, 2, 1, 1));
		this.waterColorMultiplier = 0x00FF00;
	}
	
	@Override public int getModdedBiomeGrassColor(int original) {
		return 0x00FF00;
	}
	
	@Override public int getModdedBiomeFoliageColor(int original) {
		return 0x00FF00;
	}
	
	@Override public int getSkyColorByTemp(float par1) {
		return 0x00FF00;
	}
}
