package com.zalthonethree.zombieinfection.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;

public class ZombieInfectionCodec extends FMLIndexedMessageToMessageCodec<ZIMessage>/*, EntityDragon*/ {
	
	public ZombieInfectionCodec() {
		addDiscriminator(0, ZITimeInfectedMessage.class);
	}
	
	@Override public void encodeInto(ChannelHandlerContext ctx, ZIMessage msg, ByteBuf target) throws Exception {
		target.writeInt(msg.index);
		switch (msg.index) {
			case 0:
				target.writeInt(((ZITimeInfectedMessage) msg).secondsInfected);
				break;
		}
	}
	
	@Override public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, ZIMessage msg) {
		int index = source.readInt();
		switch (index) {
			case 0:
				((ZITimeInfectedMessage) msg).secondsInfected = source.readInt();
				break;
		}
	}
}