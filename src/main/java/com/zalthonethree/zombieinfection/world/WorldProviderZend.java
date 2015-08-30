package com.zalthonethree.zombieinfection.world;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.zalthonethree.zombieinfection.handler.ConfigurationHandler;

public class WorldProviderZend extends WorldProvider {
	@Override public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerZend(worldObj.getSeed(), this.worldObj.getWorldInfo().getTerrainType());
		this.dimensionId = ConfigurationHandler.getZendDimensionId();
		this.hasNoSky = false;
	}
	
	@Override public IChunkProvider createChunkGenerator() { return new ChunkProviderZend(this.worldObj, this.worldObj.getSeed()); }
	@Override public String getDimensionName() { return "Zend"; }
	@Override @SideOnly(Side.CLIENT) public Vec3 getFogColor(float param1, float param2) { return new Vec3(0, 1, 0); }
	@Override @SideOnly(Side.CLIENT) public boolean doesXZShowFog(int x, int z) { return true; }
	@Override public boolean isSurfaceWorld() { return false; }
	@Override @SideOnly(Side.CLIENT) public float getCloudHeight() { return 8.0F; }
	@Override public boolean canDoRainSnowIce(Chunk chunk) { return false; }
	@Override public boolean canDoLightning(Chunk chunk) { return false; }
	@Override public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_) { return 0.0F; }
	@Override @SideOnly(Side.CLIENT) public float[] calcSunriseSunsetColors(float p_76560_1_, float p_76560_2_) { return null; }
	@Override @SideOnly(Side.CLIENT) public boolean isSkyColored() { return true; }
	@Override public String getInternalNameSuffix() { return ""; }
	@Override public boolean canCoordinateBeSpawn(int x, int z) { return this.worldObj.getGroundAboveSeaLevel(new BlockPos(x, 0, z)).getMaterial().blocksMovement(); }
	@Override public BlockPos getSpawnCoordinate() { return new BlockPos(100, 50, 0); }
	@Override public boolean canRespawnHere() { return false; }
	@Override public String getWelcomeMessage() { return StatCollector.translateToLocal("zombieinfection.welcomemessage.zend"); }
	@Override public String getDepartMessage() { return StatCollector.translateToLocal("zombieinfection.departmessage.zend"); }
	@Override public boolean isDaytime() { return false; }
	@Override public float getSunBrightnessFactor(float par1) { return 0.1F; }
	@Override @SideOnly(Side.CLIENT) public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) { return new Vec3(0, 1, 0); }
	@Override public void setWorldTime(long time) { this.worldObj.getWorldInfo().setWorldTime(16000); }
	@Override public long getWorldTime() { return 16000; }
	
	@Override protected void generateLightBrightnessTable() {
		float f = 0.05F;
		
		for (int i = 0; i <= 15; ++ i) {
			float f1 = 1.0F - i / 15.0F;
			lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
		}
	}
}
