package com.liws.handler;

import com.liws.entity.MsgHead;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class ServerHandler extends ChannelInboundHandlerAdapter {
    // 用于获取客户端发送的信息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 用于获取客户端发来的数据信息
        MsgHead head = (MsgHead) msg;
        if (head.getCommandID() == 0x00000001) {
            MsgHead response = new MsgHead(30,0x80000001,head.getSequenceID());
            ctx.writeAndFlush(response);
        } else if (head.getCommandID() == 0x00000008) {
            MsgHead response = new MsgHead(13,0x80000008,head.getSequenceID());
            ctx.writeAndFlush(response);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        // cause.printStackTrace();
        ctx.close();
    }
}