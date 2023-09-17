package com.rahulg.netty.sample1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SimpleProcessingHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf temp;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        System.out.println("Channel hanlder added...");
        temp  = ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        System.out.println("Hanlder removed..");
        temp.release();
        temp = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf message = (ByteBuf) msg;
        temp.writeBytes(message);
        message.release();
        System.out.println("processing...");
        if (temp.readableBytes() >= 4) {
            int intValue = temp.readInt();
            System.out.println("processing..." + intValue);
            RequestData requestData = new RequestData(intValue);
            ResponseData resp = new ResponseData(requestData.calculate());
            ChannelFuture chf = ctx.writeAndFlush(resp);
            chf.addListener(ChannelFutureListener.CLOSE);
        }
    }

}
