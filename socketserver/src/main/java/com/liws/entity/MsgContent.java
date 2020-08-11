package com.liws.entity;

import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author liws
 * @description TODO
 * @date 2020/8/11 22:21
 **/
@Component
public class MsgContent {
    private MsgHead head;
    private byte[] body;

    public MsgHead getHead() {
        return head;
    }

    public void setHead(MsgHead head) {
        this.head = head;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "MsgContent{" +
                "head=" + head +
                ", body=" + Arrays.toString(body) +
                '}';
    }
}