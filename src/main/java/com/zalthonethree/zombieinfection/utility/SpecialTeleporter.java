package com.zalthonethree.zombieinfection.utility;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class SpecialTeleporter extends Teleporter {
	protected final WorldServer worldServer;
	
	public SpecialTeleporter(WorldServer worldServer) {
		super(worldServer);
		this.worldServer = worldServer;
	}
	
	public static void adjustPosY(Entity entity) {
		int x = MathHelper.floor_double(entity.posX);
		int z = MathHelper.floor_double(entity.posZ);
		int y = entity.worldObj.getHeight(new BlockPos(x, 128, z)).getY();
		switch (entity.worldObj.provider.getDimensionId()) {
			case -1:
				y -= 10;
				boolean flag = true;
				while (y > 30 && flag) {
					if (entity.worldObj.getBlockState(new BlockPos(x, y, z)).getBlock().getMaterial().blocksMovement() && entity.worldObj.isAirBlock(new BlockPos(x, y + 1, z)) && entity.worldObj.isAirBlock(new BlockPos(x, y + 2, z))) {
						flag = false;
					} else {
						-- y;
					}
				}
				break;
			default:
		}
		if (entity instanceof EntityPlayer) {
			((EntityPlayer) entity).setPositionAndUpdate((double) x + 0.5D, y + 1, (double) z + 0.5D);
		} else {
			entity.setPosition((double) x + 0.5D, y + 1, (double) z + 0.5D);
		}
	}
	
	@Override
	public boolean makePortal(Entity entity) {
		return true;
	}
	
	@Override public void removeStalePortalLocations(long par1) {}
}