package com.soul.fileattente.api;

import android.util.Log;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class NumeroSuivantWebSocketListener {

//    private void start() {
//
//        Request request = new Request.Builder().url("ws://echo.websocket.org").build();
//        EchoWebSocketListener listener = new EchoWebSocketListener();
//        WebSocket webSocket = mClient.newWebSocket(request, listener);
//        mClient.dispatcher().executorService().shutdown();
//    }
//
//    private final class EchoWebSocketListener extends WebSocketListener {
//        private static final int CLOSE_STATUS = 1000;
//        @Override
//        public void onOpen(WebSocket webSocket, Response response) {
//            webSocket.send("What's up ?");
//            webSocket.send(ByteString.decodeHex("abcd"));
//            webSocket.close(CLOSE_STATUS, "Socket Closed !!");
//        }
//        @Override
//        public void onMessage(WebSocket webSocket, String message) {
//            print("Receive Message: " + message);
//        }
//        @Override
//        public void onMessage(WebSocket webSocket, ByteString bytes) {
//            print("Receive Bytes : " + bytes.hex());
//        }
//        @Override
//        public void onClosing(WebSocket webSocket, int code, String reason) {
//            webSocket.close(CLOSE_STATUS, null);
//            print("Closing Socket : " + code + " / " + reason);
//        }
//        @Override
//        public void onFailure(WebSocket webSocket, Throwable throwable, Response response) {
//            print("Error : " + throwable.getMessage());
//        }
//    }
//
////    private void print(final String message) {
////        runOnUiThread(new Runnable() {
////            @Override
////            public void run() {
////                //textResult.setText(textResult.getText().toString() + "n" + message);
////                Log.e("WSocket", message);
////                System.out.println("WSocket  -----> " + message);
////            }
////        });
////    }
//
//    private void print(final String message) {
//        Log.e("WSocket", message);
//        System.out.println("WSocket  -----> " + message);
//     }


}
