package com.zalthonethree.zombieinfection.handler.foodchange;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import cpw.mods.fml.client.FMLClientHandler;

public class ZIFoodChangeMessageHandler extends SimpleChannelInboundHandler<ZIFoodChangeMessage>/*, EntityDragon*/{
	@Override protected void channelRead0(ChannelHandlerContext ctx, ZIFoodChangeMessage msg) throws Exception {
		FMLClientHandler.instance().getClientPlayerEntity().getFoodStats().setFoodLevel(msg.foodLevelChange);
		FMLClientHandler.instance().getClientPlayerEntity().getFoodStats().setFoodSaturationLevel(msg.saturationChange);
	}
}