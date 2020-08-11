package com.liws.handler;

import com.liws.entity.LoginAuthReqMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liws
 * @description 登录解码器
 * @date 2020/8/11 22:10
 **/
public class LoginAuthRespHandler extends ChannelInboundHandlerAdapter {
    private Map<String, Boolean> nodeCheck = new ConcurrentHashMap<String, Boolean>();
    private String[] whitekList = {"127.0.0.1", "192.168.1.104"};

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LoginAuthReqMessage loginAuthReqMessage = (LoginAuthReqMessage) msg;


    }


    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        nodeCheck.remove(ctx.channel().remoteAddress().toString());// 删除缓存
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }
}