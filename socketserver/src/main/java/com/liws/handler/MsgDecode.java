package com.liws.handler;

import com.liws.entity.MsgHead;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MsgDecode extends ByteToMessageDecoder {

    private static final int MIN_DATA_LEN = 12;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() >= MIN_DATA_LEN) {
            // 读取消息head
            MsgHead head = new MsgHead();
            head.setMsgLen(byteBuf.readUnsignedInt());
            head.setCommandID(byteBuf.readUnsignedInt());
            head.setSequenceID(byteBuf.readUnsignedInt());
            //读取消息体
            byte[] body = new byte[(int) head.getMsgLen() -12];
            byteBuf.readBytes(body);
            list.add(head);
        }
    }
}