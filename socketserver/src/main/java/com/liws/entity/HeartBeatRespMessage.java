package com.liws.entity;

/**
 * @author liws
 * @description TODO
 * @date 2020/8/11 21:59
 **/
public class HeartBeatRespMessage {
    private MsgHead head;
    /*心跳应答结果*/
    private byte reserved;

    public MsgHead getHead() {
        return head;
    }

    public void setHead(MsgHead head) {
        this.head = head;
    }

    public byte getReserved() {
        return reserved;
    }

    public void setReserved(byte reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return "HeartBeatRespMessage{" +
                "head=" + head +
                ", reserved=" + reserved +
                '}';
    }
}