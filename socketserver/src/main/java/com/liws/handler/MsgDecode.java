package com.liws.handler;

import com.liws.entity.MsgContent;
import com.liws.entity.MsgHead;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MsgDecode extends ByteToMessageDecoder {

    private static final int MIN_DATA_LEN = 12;
    @Autowired
    private MsgContent message;
    @Autowired
    private MsgHead head;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() >= MIN_DATA_LEN) {
            // 读取消息head
            message.setHead(head);
            message.getHead().setMsgLen(byteBuf.readUnsignedInt());
            message.getHead().setCommandID(byteBuf.readUnsignedInt());
            message.getHead().setSequenceID(byteBuf.readUnsignedInt());
            //读取消息体
            byte[] body = new byte[(int) message.getHead().getMsgLen()-12];
            byteBuf.readBytes(body);
            message.setBody(body);
            System.out.println("=================================================================");
            System.out.println(message);
            System.out.println("=================================================================");
            list.add(message);
        }
    }
}