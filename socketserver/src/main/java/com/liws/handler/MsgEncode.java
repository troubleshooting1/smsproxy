package com.liws.handler;

import com.liws.entity.MsgContent;
import com.liws.entity.MsgHead;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgEncode extends MessageToByteEncoder<MsgContent> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MsgContent msg, ByteBuf out) throws Exception {
        System.out.println("-----------------------------------------------------------------");
        if (msg.getHead().getCommandID() == 0x80000001) {
            out.writeInt((int) msg.getHead().getMsgLen());
            out.writeInt((int) msg.getHead().getCommandID());
            out.writeInt((int) msg.getHead().getSequenceID());
            out.writeByte(0);
            byte[] data = new byte[17];
            out.writeBytes(data);
        } else if (msg.getHead().getCommandID() == 0x80000008) {
            out.writeInt((int) msg.getHead().getMsgLen());
            out.writeInt((int) msg.getHead().getCommandID());
            out.writeInt((int) msg.getHead().getSequenceID());
            out.writeByte(1);
        }
    }
}