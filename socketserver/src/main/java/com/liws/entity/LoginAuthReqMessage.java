package com.liws.entity;

/**
 * @description 登录请求
 * @author liws
 * @date 2020/8/11 21:39
 **/
public class LoginAuthReqMessage {
    private MsgHead head;
    /*企业sp代码*/
    private String spId;
    /*认证md5 hash值*/
    private String authenticatorSource;
    /*版本号*/
    private byte version;
    /*时间戳*/
    private int timestamp;

    public MsgHead getHead() {
        return head;
    }

    public void setHead(MsgHead head) {
        this.head = head;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getAuthenticatorSource() {
        return authenticatorSource;
    }

    public void setAuthenticatorSource(String authenticatorSource) {
        this.authenticatorSource = authenticatorSource;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "LoginAuthReqMessage{" +
                "head=" + head +
                ", spId='" + spId + '\'' +
                ", authenticatorSource='" + authenticatorSource + '\'' +
                ", version=" + version +
                ", timestamp=" + timestamp +
                '}';
    }
}