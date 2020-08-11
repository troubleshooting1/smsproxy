package com.liws.handler;

import com.liws.entity.MsgHead;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgEncode extends MessageToByteEncoder<MsgHead> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MsgHead msg, ByteBuf out) throws Exception {
        if (msg.getCommandID() == 0x80000001) {
            out.writeInt((int) msg.getMsgLen());
            out.writeInt((int) msg.getCommandID());
            out.writeInt((int) msg.getSequenceID());
            out.writeByte(0);
            byte[] data = new byte[17];
            out.writeBytes(data);
        } else if (msg.getCommandID() == 0x80000008) {
            out.writeInt((int) msg.getMsgLen());
            out.writeInt((int) msg.getCommandID());
            out.writeInt((int) msg.getSequenceID());
            out.writeByte(1);
        }
    }
}