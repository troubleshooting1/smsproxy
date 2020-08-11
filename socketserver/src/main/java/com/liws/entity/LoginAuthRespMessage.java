package com.liws.entity;

/**
 * @author liws
 * @description 登录响应
 * @date 2020/8/11 21:51
 **/
public class LoginAuthRespMessage {
    private MsgHead head;
    /*登录结果*/
    private byte status;
    /*认证md5 hash值*/
    private String authenticatorISMG;
    /*版本号*/
    private byte version;

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

    public String getAuthenticatorISMG() {
        return authenticatorISMG;
    }

    public void setAuthenticatorISMG(String authenticatorISMG) {
        this.authenticatorISMG = authenticatorISMG;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "LoginAuthRespMessage{" +
                "head=" + head +
                ", status=" + status +
                ", authenticatorISMG='" + authenticatorISMG + '\'' +
                ", version=" + version +
                '}';
    }
}