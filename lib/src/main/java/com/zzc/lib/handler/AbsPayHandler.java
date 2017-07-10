package com.zzc.lib.handler;

import android.app.Activity;

import com.zzc.lib.ResultListener;
import com.zzc.lib.order.OrderGenerator;

/**
 * Created : zzc
 * Time : 2017/6/15
 * Email : zzc1259@163.com
 * Description : ${desc}
 */

public abstract class AbsPayHandler<T extends OrderGenerator> {
    protected ResultListener mListener;

    public abstract void pay(Activity activity, T order);

    public void setOnResult(ResultListener listener) {
        mListener = listener;
    }

    public abstract void release();
}
