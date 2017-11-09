package com.zzc.lib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zzc.lib.handler.WechatPayHandler;
import com.zzc.lib.model.WechatPayResult;


public abstract class WXCallbackActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        api = WXAPIFactory.createWXAPI(this, appId());
        api.handleIntent(getIntent(), this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
            Intent intent = new Intent(PayManager.ACTION_WECHAT);
            intent.putExtra(WechatPayHandler.WX_PAY_KEY,new WechatPayResult(resp.errCode==0,resp.transaction));
            manager.sendBroadcast(intent);
            finish();
        }
    }

    protected abstract String appId();
}