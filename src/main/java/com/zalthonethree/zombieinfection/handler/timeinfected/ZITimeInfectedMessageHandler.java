package com.zalthonethree.zombieinfection.handler.timeinfected;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.zalthonethree.zombieinfection.utility.TimeInfectedTrackingClient;

public class ZITimeInfectedMessageHandler extends SimpleChannelInboundHandler<ZITimeInfectedMessage> {
	@Override protected void channelRead0(ChannelHandlerContext ctx, ZITimeInfectedMessage msg) throws Exception {
		TimeInfectedTrackingClient.setSecondsInfected(msg.secondsInfected);
	}
}