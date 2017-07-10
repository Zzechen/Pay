package com.zzc.lib.order;

/**
 * Created : zzc
 * Time : 2017/6/16
 * Email : zzc1259@163.com
 * Description : ${交易订单生成}
 */

public abstract class OrderGenerator<T, V> {
    protected T mSrc;

    public OrderGenerator(T t) {
        this.mSrc = t;
    }

    public abstract V convert();

    public V generator() {
        return convert();
    }
}
