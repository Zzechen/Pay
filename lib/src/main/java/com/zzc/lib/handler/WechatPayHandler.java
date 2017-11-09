package com.zzc.lib.handler;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zzc.lib.PayManager;
import com.zzc.lib.ResultListener;
import com.zzc.lib.model.WechatPayResult;
import com.zzc.lib.order.WechatOrderGenerator;

/**
 * Created : zzc
 * Time : 2017/6/15
 * Email : zzc1259@163.com
 * Description : ${desc}
 */

public class WechatPayHandler extends AbsPayHandler<WechatOrderGenerator> {
    public static final String WX_PAY_KEY = "wechatPayResultKey";
    private IWXAPI mWxapi;
    private LocalBroadcastManager mBroadcastManager;
    private WXPayReceiver mWxPayReceiver;

    @Override
    public void pay(Activity activity, WechatOrderGenerator order) {
        mWxapi = WXAPIFactory.createWXAPI(activity, "wxd930ea5d5a258f4f");
        mWxapi.registerApp("wxd930ea5d5a258f4f");
        mWxapi.sendReq(order.generator());
        mBroadcastManager = LocalBroadcastManager.getInstance(activity);
        mWxPayReceiver = new WXPayReceiver(mListener);
        mBroadcastManager.registerReceiver(mWxPayReceiver, new IntentFilter(PayManager.ACTION_WECHAT));
    }

    @Override
    public void release() {
        mBroadcastManager.unregisterReceiver(mWxPayReceiver);
        mBroadcastManager = null;
        mWxapi = null;
        mListener = null;
    }

    private static class WXPayReceiver extends BroadcastReceiver {
        private ResultListener mResultListener;

        public WXPayReceiver(ResultListener listener) {
            mResultListener = listener;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && PayManager.ACTION_WECHAT.equals(intent.getAction())) {
                if (mResultListener != null) {
                    WechatPayResult result = intent.getParcelableExtra(WX_PAY_KEY);
                    if (result.isSuccess()) {
                        mResultListener.onSuccess(result.getTradeNo());
                    } else {
                        mResultListener.onFail();
                    }
                }
            }
        }
    }
}
