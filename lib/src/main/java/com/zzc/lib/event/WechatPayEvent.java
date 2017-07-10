package com.zzc.lib.event;

/**
 * Created : zzc
 * Time : 2017/6/16
 * Email : zzc1259@163.com
 * Description : ${desc}
 */

public class WechatPayEvent {
    private boolean isSuccess;
    private String tradeNo;

    public WechatPayEvent(boolean isSuccess, String tradeNo) {
        this.isSuccess = isSuccess;
        this.tradeNo = tradeNo;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getTradeNo() {
        return tradeNo;
    }
}
