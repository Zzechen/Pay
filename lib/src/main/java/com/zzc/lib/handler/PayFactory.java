package com.zzc.lib.handler;

import com.zzc.lib.PayManager;

/**
 * Created : zzc
 * Time : 2017/6/15
 * Email : zzc1259@163.com
 * Description : ${desc}
 */

public class PayFactory {
    public static AbsPayHandler create(@PayManager.PayWay int way) {
        switch (way) {
            case PayManager.ALIPAY:
                return new AliPayHandler();
            case PayManager.WECHAT:
                return new WechatPayHandler();
            default:
                throw new IllegalArgumentException("no such method for account:" + way);
        }
    }
}
