package com.liws.entity;

public class ResponseMsg {
    public MsgHead head;
    public byte status;

    public ResponseMsg() {
    }

    public ResponseMsg(MsgHead head, byte status) {
        this.head = head;
        this.status = status;
    }

    public MsgHead getHead() {
        return head;
    }

    public void setHead(MsgHead head) {
        this.head = head;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResponseMsg{" +
                "head=" + head +
                ", status=" + status +
                '}';
    }
}