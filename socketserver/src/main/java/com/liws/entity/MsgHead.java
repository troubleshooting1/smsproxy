package com.liws.entity;

import org.springframework.stereotype.Component;

@Component
public class MsgHead {
    /**
     * 消息总长度
     */
    public long msgLen;
    /**
     * 请求/响应类型
     */
    public long commandID;
    /**
     * 消息流水
     */
    public long sequenceID;

    public MsgHead() {
    }

    public MsgHead(long msgLen, long commandID, long sequenceID) {
        this.msgLen = msgLen;
        this.commandID = commandID;
        this.sequenceID = sequenceID;
    }

    public long getMsgLen() {
        return msgLen;
    }

    public void setMsgLen(long msgLen) {
        this.msgLen = msgLen;
    }

    public long getCommandID() {
        return commandID;
    }

    public void setCommandID(long commandID) {
        this.commandID = commandID;
    }

    public long getSequenceID() {
        return sequenceID;
    }

    public void setSequenceID(long sequenceID) {
        this.sequenceID = sequenceID;
    }

    @Override
    public String toString() {
        return "MsgHead{" +
                "msgLen=" + msgLen +
                ", commandID=" + commandID +
                ", sequenceID=" + sequenceID +
                '}';
    }
}