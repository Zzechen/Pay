package com.zzc.lib.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @auth zzc
 * @date 2017/11/9
 * @desc ${微信支付结果}
 */

public class WechatPayResult implements Parcelable {
    private boolean isSuccess;
    private String tradeNo;

    public WechatPayResult(boolean isSuccess, String tradeNo) {
        this.isSuccess = isSuccess;
        this.tradeNo = tradeNo;
    }

    protected WechatPayResult(Parcel in) {
        isSuccess = in.readByte() != 0;
        tradeNo = in.readString();
    }

    public static final Creator<WechatPayResult> CREATOR = new Creator<WechatPayResult>() {
        @Override
        public WechatPayResult createFromParcel(Parcel in) {
            return new WechatPayResult(in);
        }

        @Override
        public WechatPayResult[] newArray(int size) {
            return new WechatPayResult[size];
        }
    };

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isSuccess ? 1 : 0));
        dest.writeString(tradeNo);
    }
}
