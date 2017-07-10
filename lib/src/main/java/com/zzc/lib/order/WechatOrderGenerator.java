package com.zzc.lib.order;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.zzc.lib.model.WeChatReq;

/**
 * Created : zzc
 * Time : 2017/6/16
 * Email : zzc1259@163.com
 * Description : ${desc}
 */

public class WechatOrderGenerator extends OrderGenerator<WeChatReq, PayReq> {

    public WechatOrderGenerator(WeChatReq req) {
        super(req);
    }

    @Override
    public PayReq convert() {
        PayReq payReq = new PayReq();
        payReq.appId = mSrc.getAppId();
        payReq.partnerId = mSrc.getPartnerId();
        payReq.prepayId = mSrc.getPrepayId();
        payReq.packageValue = mSrc.getPackageValue();
        payReq.nonceStr = mSrc.getNonceStr();
        payReq.timeStamp = mSrc.getTimeStamp();
        payReq.sign = mSrc.getSign();
        return payReq;
    }
//    @Override
//    public PayReq createOrder() {
//        PayReq req = new PayReq();
//        req.appId = getAppId();
//        req.partnerId = getPartnerId();
//        req.prepayId = getPrepayId();
//        req.nonceStr = getNonceString();
//        req.timeStamp = getTimestamp();
//        req.packageValue = getPackageValue();
//        req.sign = getSign();
//        return req;
//    }

//    /**
//     * 公众账号Id
//     *
//     * @return
//     */
//    protected abstract String getAppId();
//
//    /**
//     * 签名,具体请看微信文档解释
//     *
//     * @return
//     */
//    protected abstract String getSign();
//
//
//    /**
//     * 预交易会话Id,由服务端向微信请求而来
//     *
//     * @return
//     */
//    protected abstract String getPrepayId();
//
//    /**
//     * 随机字符串，具体请看微信文档解释
//     *
//     * @return
//     */
//    protected abstract String getNonceString();
//
//    /**
//     * 时间戳，具体请看微信文档解释
//     *
//     * @return
//     */
//    protected abstract String getTimestamp();
//
//    /**
//     * 扩展字段，目前固定值
//     *
//     * @return
//     */
//    private String getPackageValue() {
//        return "Sign=WXPay";
//    }
}
