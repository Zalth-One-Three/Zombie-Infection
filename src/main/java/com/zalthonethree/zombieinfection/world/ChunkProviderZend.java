package com.zalthonethree.zombieinfection.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.zalthonethree.zombieinfection.init.ModBlocks;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class ChunkProviderZend implements IChunkProvider {
	public static int topOfStructure = 0;
	
	private Random rand;
	private NoiseGeneratorOctaves noiseGen1;
	private NoiseGeneratorOctaves noiseGen2;
	private NoiseGeneratorOctaves noiseGen3;
	public NoiseGeneratorOctaves noiseGen4;
	public NoiseGeneratorOctaves noiseGen5;
	
	private World worldObj;
	private double[] densities;
	private BiomeGenBase[] biomesForGeneration;
	double[] noiseData1;
	double[] noiseData2;
	double[] noiseData3;
	double[] noiseData4;
	double[] noiseData5;
	public static ArrayList<BlockPos> endStoneLocations = new ArrayList<BlockPos>();
	
	public ChunkProviderZend(World world, long seed) {
		this.worldObj = world;
		this.rand = new Random(seed);
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
		this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 10);
		this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 16);

		NoiseGenerator[] noiseGens = {noiseGen1, noiseGen2, noiseGen3, noiseGen4, noiseGen5};
		noiseGens = TerrainGen.getModdedNoiseGenerators(world, this.rand, noiseGens);
		this.noiseGen1 = (NoiseGeneratorOctaves) noiseGens[0];
		this.noiseGen2 = (NoiseGeneratorOctaves) noiseGens[1];
		this.noiseGen3 = (NoiseGeneratorOctaves) noiseGens[2];
		this.noiseGen4 = (NoiseGeneratorOctaves) noiseGens[3];
		this.noiseGen5 = (NoiseGeneratorOctaves) noiseGens[4];
	}
	
	public void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer primer) {
		byte b0 = 2;
		int k = b0 + 1;
		byte b1 = 33;
		int l = b0 + 1;
		this.densities = this.initializeNoiseField(this.densities, chunkX * b0, 0, chunkZ * b0, k, b1, l);
		
		for (int i1 = 0; i1 < b0; i1 ++) {
			for (int j1 = 0; j1 < b0; j1 ++) {
				for (int k1 = 0; k1 < 32; k1 ++) {
					double d0 = 0.25D;
					double d1 = this.densities[((i1 + 0) * l + j1 + 0) * b1 + k1 + 0];
					double d2 = this.densities[((i1 + 0) * l + j1 + 1) * b1 + k1 + 0];
					double d3 = this.densities[((i1 + 1) * l + j1 + 0) * b1 + k1 + 0];
					double d4 = this.densities[((i1 + 1) * l + j1 + 1) * b1 + k1 + 0];
					double d5 = (this.densities[((i1 + 0) * l + j1 + 0) * b1 + k1 + 1] - d1) * d0;
					double d6 = (this.densities[((i1 + 0) * l + j1 + 1) * b1 + k1 + 1] - d2) * d0;
					double d7 = (this.densities[((i1 + 1) * l + j1 + 0) * b1 + k1 + 1] - d3) * d0;
					double d8 = (this.densities[((i1 + 1) * l + j1 + 1) * b1 + k1 + 1] - d4) * d0;
					
					for (int l1 = 0; l1 < 4; l1 ++) {
						double d9 = 0.125D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;
						
						for (int i2 = 0; i2 < 8; i2 ++) {
							double d14 = 0.125D;
							double d15 = d10;
							double d16 = (d11 - d10) * d14;
							
							for (int j2 = 0; j2 < 8; j2 ++) {
								IBlockState iblockstate = null;
								
								if (d15 > 0.0D) {
									iblockstate = ModBlocks.zendStone.getDefaultState();
								}
								
								int k2 = i2 + i1 * 8;
								int l2 = l1 + k1 * 4;
								int i3 = j2 + j1 * 8;
								primer.setBlockState(k2, l2, i3, iblockstate);
								d15 += d16;
							}
							
							d10 += d12;
							d11 += d13;
						}
						
						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}
	
	public void replaceBlocksForBiome(int chunkX, int chunkZ, ChunkPrimer primer, BiomeGenBase[] biomes) {
		ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, chunkX, chunkZ, primer, this.worldObj);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.getResult() == Result.DENY) return;
		for (int i = 0; i < 16; i ++) {
			for (int j = 0; j < 16; j ++) {
				byte b0 = 1;
				int k = -1;
				IBlockState iblockstate = Blocks.end_stone.getDefaultState();
				IBlockState iblockstate1 = Blocks.end_stone.getDefaultState();
				
				for (int l = 127; l >= 0; l --) {
					IBlockState iblockstate2 = primer.getBlockState(i, l, j);
					
					if (iblockstate2.getBlock().getMaterial() == Material.air) {
						k = -1;
					} else if (iblockstate2.getBlock() == Blocks.stone) {
						if (k == -1) {
							if (b0 <= 0) {
								iblockstate = Blocks.air.getDefaultState();
								iblockstate1 = Blocks.end_stone.getDefaultState();
							}
							
							k = b0;
							
							if (l >= 0) {
								primer.setBlockState(i, l, j, iblockstate);
							} else {
								primer.setBlockState(i, l, j, iblockstate1);
							}
						} else if (k > 0) {
							k --;
							primer.setBlockState(i, l, j, iblockstate1);
						}
					}
				}
			}
		}
	}
	
	boolean topOfStructureGotten = false;
	IBlockState STRUCTURE_BLOCK = Blocks.end_stone.getDefaultState();
	
	void createDragonStructure(ChunkPrimer primer, int chunkX, int chunkZ) {
		if (!topOfStructureGotten) {
			for (int i = 256; i > 0; i --) {
				if (primer.getBlockState(0, i - 1, 0).getBlock() == Blocks.air) continue;
				topOfStructure = i;
				break;
			}
			topOfStructureGotten = true;
		}
		
		if (chunkX == 0 && chunkZ == 0) {
			int iteration = 1;
			
			primer.setBlockState(0, topOfStructure + 5, 0, ModBlocks.dragonSpawner.getDefaultState());
			for (int i = topOfStructure + 4; i >= topOfStructure; i --) {
				for (int x = 0; x < iteration; x ++) {
					for (int z = 0; z < iteration; z ++) {
						if (primer.getBlockState(x, i, z).getBlock() == Blocks.air) {
							primer.setBlockState(x, i, z, STRUCTURE_BLOCK);
							endStoneLocations.add(new BlockPos((chunkX * 16) + x, i, (chunkZ * 16) + z));
						}
					}
				}
				iteration ++;
			}
		} else if (chunkX == 0 && chunkZ == -1) {
			int iteration = 1;
			
			for (int i = topOfStructure + 4; i >= topOfStructure; i --) {
				for (int x = 0; x < iteration; x ++) {
					for (int z = 15; z > (15 - iteration); z --) {
						if (primer.getBlockState(x, i, z).getBlock() == Blocks.air) {
							primer.setBlockState(x, i, z, STRUCTURE_BLOCK);
							endStoneLocations.add(new BlockPos((chunkX * 16) + x, i, (chunkZ * 16) + z));
						}
					}
				}
				iteration ++;
			}
		} else if (chunkX == -1 && chunkZ == 0) {
			int iteration = 1;
			
			for (int i = topOfStructure + 4; i >= topOfStructure; i --) {
				for (int x = 15; x > (15 - iteration); x --) {
					for (int z = 0; z < iteration; z ++) {
						if (primer.getBlockState(x, i, z).getBlock() == Blocks.air) {
							primer.setBlockState(x, i, z, STRUCTURE_BLOCK);
							endStoneLocations.add(new BlockPos((chunkX * 16) + x, i, (chunkZ * 16) + z));
						}
					}
				}
				iteration ++;
			}
		} else if (chunkX == -1 && chunkZ == -1) {
			int iteration = 1;
			
			for (int i = topOfStructure + 4; i >= topOfStructure; i --) {
				for (int x = 15; x > (15 - iteration); x --) {
					for (int z = 15; z > (15 - iteration); z --) {
						if (primer.getBlockState(x, i, z).getBlock() == Blocks.air) {
							primer.setBlockState(x, i, z, STRUCTURE_BLOCK);
							endStoneLocations.add(new BlockPos((chunkX * 16) + x, i, (chunkZ * 16) + z));
						}
					}
				}
				iteration ++;
			}
		}
	}
	
	@Override public Chunk provideChunk(int chunkX, int chunkZ) {
		this.rand.setSeed((long) chunkX * 341873128712L + (long) chunkZ * 132897987541L);
		ChunkPrimer primer = new ChunkPrimer();
		this.setBlocksInChunk(chunkX, chunkZ, primer);
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
		this.replaceBlocksForBiome(chunkX, chunkZ, primer, this.biomesForGeneration);
		if ((chunkX == -1 && chunkZ == 0) || (chunkX == -1 && chunkZ == -1) || (chunkX == 0 && chunkZ == -1) || (chunkX == 0 && chunkZ == 0)) createDragonStructure(primer, chunkX, chunkZ);
		Chunk chunk = new Chunk(this.worldObj, primer, chunkX, chunkZ);
		byte[] abyte = chunk.getBiomeArray();
		
		for (int k = 0; k < abyte.length; k ++) {
			abyte[k] = (byte) this.biomesForGeneration[k].biomeID;
		}
		
		chunk.generateSkylightMap();
		return chunk;
	}
	
	private double[] initializeNoiseField(double[] noiseField, int x, int y, int z, int width, int height, int length) {
		ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField(this, noiseField, x, y, z, width, height, length);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.getResult() == Result.DENY) return event.noisefield;
		
		if (noiseField == null) {
			noiseField = new double[width * height * length];
		}
		
		double d0 = 684.412D;
		double d1 = 684.412D;
		this.noiseData4 = this.noiseGen4.generateNoiseOctaves(this.noiseData4, x, z, width, length, 1.121D, 1.121D, 0.5D);
		this.noiseData5 = this.noiseGen5.generateNoiseOctaves(this.noiseData5, x, z, width, length, 200.0D, 200.0D, 0.5D);
		d0 *= 2.0D;
		this.noiseData1 = this.noiseGen3.generateNoiseOctaves(this.noiseData1, x, y, z, width, height, length, d0 / 80.0D, d1 / 160.0D, d0 / 80.0D);
		this.noiseData2 = this.noiseGen1.generateNoiseOctaves(this.noiseData2, x, y, z, width, height, length, d0, d1, d0);
		this.noiseData3 = this.noiseGen2.generateNoiseOctaves(this.noiseData3, x, y, z, width, height, length, d0, d1, d0);
		int k1 = 0;
		
		for (int l1 = 0; l1 < width; l1 ++) {
			for (int i2 = 0; i2 < length; i2 ++) {
				float f = (float) (l1 + x) / 1.0F;
				float f1 = (float) (i2 + z) / 1.0F;
				float f2 = 100.0F - MathHelper.sqrt_float(f * f + f1 * f1) * 8.0F;
				
				if (f2 > 80.0F) {
					f2 = 80.0F;
				}
				
				if (f2 < -100.0F) {
					f2 = -100.0F;
				}
				
				for (int j2 = 0; j2 < height; j2 ++) {
					double d2 = 0.0D;
					double d3 = this.noiseData2[k1] / 512.0D;
					double d4 = this.noiseData3[k1] / 512.0D;
					double d5 = (this.noiseData1[k1] / 10.0D + 1.0D) / 2.0D;
					
					if (d5 < 0.0D) {
						d2 = d3;
					} else if (d5 > 1.0D) {
						d2 = d4;
					} else {
						d2 = d3 + (d4 - d3) * d5;
					}
					
					d2 -= 8.0D;
					d2 += (double) f2;
					byte b0 = 2;
					double d6;
					
					if (j2 > height / 2 - b0) {
						d6 = (double) ((float) (j2 - (height / 2 - b0)) / 64.0F);
						d6 = MathHelper.clamp_double(d6, 0.0D, 1.0D);
						d2 = d2 * (1.0D - d6) + -3000.0D * d6;
					}
					
					b0 = 8;
					
					if (j2 < b0) {
						d6 = (double) ((float) (b0 - j2) / ((float) b0 - 1.0F));
						d2 = d2 * (1.0D - d6) + -30.0D * d6;
					}
					
					noiseField[k1] = d2;
					k1 ++;
				}
			}
		}
		
		return noiseField;
	}
	
	@Override public boolean chunkExists(int chunkX, int chunkZ) {
		return true;
	}
	
	@Override public void populate(IChunkProvider provider, int chunkX, int chunkZ) {
		BlockFalling.fallInstantly = true;
		this.rand.setSeed(this.worldObj.getSeed());
		long i1 = this.rand.nextLong() / 2L * 2L + 1L;
		long j1 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed((long) chunkX * i1 + (long) chunkZ * j1 ^ this.worldObj.getSeed());
		
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(provider, worldObj, rand, chunkX, chunkZ, false));
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(provider, worldObj, rand, chunkX, chunkZ, false));
		
		BlockFalling.fallInstantly = false;
	}
	
	@Override public boolean func_177460_a(IChunkProvider provider, Chunk chunk, int chunkX, int chunkZ) {
		return false;
	}
	
	@Override public boolean saveChunks(boolean p_73151_1_, IProgressUpdate progressCallback) {
		return true;
	}
	
	@Override public void saveExtraData() {}
	
	@Override public boolean unloadQueuedChunks() {
		return false;
	}
	
	@Override public boolean canSave() {
		return true;
	}
	
	@Override public String makeString() {
		return "RandomLevelSource";
	}
	
	@SuppressWarnings("rawtypes") @Override public List getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(pos);
		return biomegenbase == null ? null : biomegenbase.getSpawnableList(creatureType);
	}
	
	@Override public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
		return null;
	}
	
	@Override public int getLoadedChunkCount() {
		return 0;
	}
	
	@Override public void recreateStructures(Chunk chunk, int chunkX, int chunkZ) {}
	
	@Override public Chunk provideChunk(BlockPos blockPosIn) {
		return this.provideChunk(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4);
	}
}