package com.zalthonethree.zombieinfection.handler;

import com.zalthonethree.zombieinfection.handler.foodchange.ZIFoodChangeMessage;
import com.zalthonethree.zombieinfection.handler.timeinfected.ZITimeInfectedMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;

public class ZombieInfectionCodec extends FMLIndexedMessageToMessageCodec<ZIMessage>/*, EntityDragon*/ {
	
	public ZombieInfectionCodec() {
		addDiscriminator(0, ZITimeInfectedMessage.class);
		addDiscriminator(1, ZIFoodChangeMessage.class);
	}
	
	@Override public void encodeInto(ChannelHandlerContext ctx, ZIMessage msg, ByteBuf target) throws Exception {
		target.writeInt(msg.index);
		switch (msg.index) {
			case 0:
				target.writeInt(((ZITimeInfectedMessage) msg).secondsInfected);
				break;
			case 1:
				target.writeInt(((ZIFoodChangeMessage) msg).foodLevelChange);
				target.writeFloat(((ZIFoodChangeMessage) msg).saturationChange);
				break;
		}
	}
	
	@Override public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, ZIMessage msg) {
		int index = source.readInt();
		switch (index) {
			case 0:
				((ZITimeInfectedMessage) msg).secondsInfected = source.readInt();
				break;
			case 1:
				((ZIFoodChangeMessage) msg).foodLevelChange = source.readInt();
				((ZIFoodChangeMessage) msg).saturationChange = source.readFloat();
		}
	}
}