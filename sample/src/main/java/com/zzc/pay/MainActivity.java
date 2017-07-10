package com.zzc.pay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zzc.lib.PayManager;
import com.zzc.lib.ResultListener;
import com.zzc.lib.model.WeChatReq;
import com.zzc.lib.order.AlipayOrderGenerator;
import com.zzc.lib.order.WechatOrderGenerator;

public class MainActivity extends AppCompatActivity implements ResultListener {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_wx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayManager payManager = new PayManager(PayManager.WECHAT);
                WeChatReq req = new WeChatReq();
                req.setAppId("wxd930ea5d5a258f4f");
                req.setPartnerId("1900000109");
                req.setNonceStr("1101000000140429eb40476f8896f4c9");
                req.setPrepayId("1101000000140415649af9fc314aa427");
                req.setPackageValue("Sign=WXPay");
                req.setSign("7FFECB600D7157C5AA49810D2D8F28BC2811827B");
                req.setTimeStamp("1398746574");
                payManager.pay(MainActivity.this, new WechatOrderGenerator(req));
                payManager.setResultListener(MainActivity.this);
            }
        });
        findViewById(R.id.btn_alipay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayManager payManager = new PayManager(PayManager.ALIPAY);
                payManager.pay(MainActivity.this, new AlipayOrderGenerator("content"));
                payManager.setResultListener(MainActivity.this);
            }
        });
        tvResult = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    public void onSuccess(String outOrderId) {
        tvResult.setText(outOrderId);
    }

    @Override
    public void onFail() {
        tvResult.setText("error");
    }
}
