package com.liws.handler;

import com.liws.entity.MsgContent;
import com.liws.entity.MsgHead;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private MsgContent msgContent;
    @Autowired
    private MsgHead head;

    // 用于获取客户端发送的信息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 用于获取客户端发来的数据信息
        MsgContent message = (MsgContent) msg;

        if (message.getHead().getCommandID() == 0x00000001) {
            msgContent.setHead(head);
            msgContent.getHead().setMsgLen(30);
            msgContent.getHead().setCommandID(0x80000001);
            msgContent.getHead().setSequenceID(1);
            //MsgHead response = new MsgHead(30,0x80000001,head.getSequenceID());
            System.out.println(msgContent);
            ctx.writeAndFlush(msgContent);

        } else if (message.getHead().getCommandID() == 0x00000008) {
            //MsgHead response = new MsgHead(13,0x80000008,head.getSequenceID());
            msgContent.setHead(head);
            msgContent.getHead().setMsgLen(13);
            msgContent.getHead().setCommandID(0x80000008);
            msgContent.getHead().setSequenceID(1);
            System.out.println(msgContent);
            ctx.writeAndFlush(msgContent);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        // cause.printStackTrace();
        ctx.close();
    }
}