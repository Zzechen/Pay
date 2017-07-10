package com.zzc.lib.handler;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.zzc.lib.order.AlipayOrderGenerator;

/**
 * Created : zzc
 * Time : 2017/6/15
 * Email : zzc1259@163.com
 * Description : ${desc}
 */

public class AliPayHandler extends AbsPayHandler<AlipayOrderGenerator> {
    private static final int PAY_RESULT = 1;
    public static final int SUCCESS = 9000;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case PAY_RESULT:
                    if (mListener != null) {
                        PayResult result = new PayResult((String) msg.obj);
                        int code = 0;
                        try {
                            code = Integer.parseInt(result.getResultStatus());
                        } catch (NumberFormatException e) {

                        }
                        if (SUCCESS == code) {
                            mListener.onSuccess(result.getTradeNo());
                        } else {
                            mListener.onFail();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void pay(Activity activity, final AlipayOrderGenerator order) {
        final PayTask alipay = new PayTask(activity);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String pay = alipay.pay(order.generator(), true);
                Message msg = Message.obtain();
                msg.what = PAY_RESULT;
                msg.obj = pay;
                mHandler.sendMessage(msg);
            }
        }).start();
    }

    @Override
    public void release() {
        mHandler.removeMessages(PAY_RESULT);
        mListener = null;
        mHandler = null;
    }
}
