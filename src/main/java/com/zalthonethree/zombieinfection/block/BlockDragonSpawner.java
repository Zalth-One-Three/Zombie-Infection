package com.zalthonethree.zombieinfection.block;

import java.util.Random;

import com.zalthonethree.zombieinfection.entity.zend.EntityZombieDragon;
import com.zalthonethree.zombieinfection.world.ChunkProviderZend;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockDragonSpawner extends BlockBase {
	public BlockDragonSpawner() {
		super(Material.rock);
		this.setUnlocalizedName("dragonSpawner");
		this.setBlockUnbreakable();
		this.setTickRandomly(true);
		this.setCreativeTab((CreativeTabs) null);
	}
	
	@Override public void randomTick(World world, BlockPos pos, IBlockState state, Random random) {
		EntityZombieDragon zombieDragon = new EntityZombieDragon(world);
		zombieDragon.setEndStoneLocations(ChunkProviderZend.endStoneLocations.toArray(new BlockPos[]{}));
		zombieDragon.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
		world.spawnEntityInWorld(zombieDragon);
		world.setBlockToAir(pos);
		super.randomTick(world, pos, state, random);
	}
	
	@Override public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		this.randomTick(world, pos, this.getDefaultState(), new Random());
		return Blocks.air.getDefaultState();
	}
}