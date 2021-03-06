package com.android.websocket;

import android.content.Context;

import com.android.db.DbHelper;
import com.android.module.Order;
import com.android.util.LogUtils;

import de.tavendo.autobahn.WebSocketConnectionHandler;

/**
 * Created by wxj on 2015-7-26.
 */
public class WebSocketConnectListener extends WebSocketConnectionHandler {
    private Context context;

    public WebSocketConnectListener(Context context) {
        this.context = context;
    }

    @Override
    public void onOpen() {
        super.onOpen();
        LogUtils.d("%s", "WebSocketConnectListener connect");
        WebSocketManager.getManager(context).sendTextMessage("mm" + "!@#$%" + "我要接单");
    }

    @Override
    public void onClose(int code, String reason) {
        super.onClose(code, reason);
        LogUtils.d("%s", "WebSocketConnectListener lost");
        Order order = new Order();
        order.setOpenId(Math.random()+"");
        DbHelper.getInstance(context).saveOrderInfo(order);
    }

    @Override
    public void onTextMessage(String payload) {
        super.onTextMessage(payload);
        Order order = new Order();
        order.setOpenId(Math.random()+"");
        DbHelper.getInstance(context).saveOrderInfo(order);
    }

}
