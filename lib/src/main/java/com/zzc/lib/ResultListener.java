package com.zzc.lib;

/**
 * Created : zzc
 * Time : 2017/6/15
 * Email : zzc1259@163.com
 * Description : ${desc}
 */

public interface ResultListener {
    void onSuccess(String outOrderId);

    void onFail();
}
