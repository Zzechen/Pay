package com.zzc.lib.handler;

import android.app.Activity;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zzc.lib.event.WechatPayEvent;
import com.zzc.lib.order.WechatOrderGenerator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created : zzc
 * Time : 2017/6/15
 * Email : zzc1259@163.com
 * Description : ${desc}
 * todo 待验证
 */

public class WechatPayHandler extends AbsPayHandler<WechatOrderGenerator> {
    private IWXAPI mWxapi;

    public WechatPayHandler() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void pay(Activity activity, WechatOrderGenerator order) {
        mWxapi = WXAPIFactory.createWXAPI(activity, "wxd930ea5d5a258f4f");
        mWxapi.registerApp("wxd930ea5d5a258f4f");
        mWxapi.sendReq(order.generator());
    }

    @Override
    public void release() {
        EventBus.getDefault().unregister(this);
        mWxapi = null;
        mListener = null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void wechatPayResult(WechatPayEvent event) {
        boolean success = event.isSuccess();
        if (mListener != null) {
            if (success) {
                mListener.onSuccess(event.getTradeNo());
            } else {
                mListener.onFail();
            }
        }
    }
}
