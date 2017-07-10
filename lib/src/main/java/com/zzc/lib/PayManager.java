package com.zzc.lib;

import android.app.Activity;
import android.support.annotation.IntDef;

import com.zzc.lib.handler.AbsPayHandler;
import com.zzc.lib.handler.PayFactory;
import com.zzc.lib.order.OrderGenerator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created : zzc
 * Time : 2017/6/15
 * Email : zzc1259@163.com
 * Description : ${支付统一接口，实例化该类，指明支付平台，设置结果监听，调用支付{@link #pay(Activity, OrderGenerator)}
 */

public class PayManager {
    public static final int ALIPAY = 1;
    public static final int WECHAT = 2;
    private final AbsPayHandler mPayHandler;

    @IntDef({WECHAT, ALIPAY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PayWay {
    }

    public PayManager(@PayWay int way) {
        mPayHandler = PayFactory.create(way);
    }

    public void pay(Activity activity, OrderGenerator orderInfo) {
        mPayHandler.pay(activity, orderInfo);
    }

    public void setResultListener(ResultListener listener) {
        mPayHandler.setOnResult(listener);
    }

    public void release() {
        mPayHandler.release();
    }
}
