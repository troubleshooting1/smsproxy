###基于Netty实现CMPP协议的服务端及客户端

####开发更新记录

#####2020-08-11
1、完成Spring整合Netty

2、完成Netty启动类的编写

3、完成解码器、编码器的简单demo


####服务端(SocketServer)开发说明
1、长连接

2、心跳会话

3、socket码流 head+body形式：
    
    消息总长(4Byte)+命令(4Byte)+流水(4Byte)+报文体(不定长)