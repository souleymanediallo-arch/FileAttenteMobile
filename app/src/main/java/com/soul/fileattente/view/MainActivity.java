//Info o the testing online
//https://www.piesocket.com/websocket-tester?url=wss%3A%2F%2Fs9042.nyc1.piesocket.com%2Fv3%2F1%3Fapi_key%3DgGbcKyjG0YlkO5UenoihzD7Bivjz7Od6JDVZCpHs%26notify_self%3D1
//Testing WebSocket for Android
//https://www.piesocket.com/app/v4/onboarding
//d_souleymane@yahoo.com
//P@sswordPs100
//valanle jusqu'au '27 Juin 2023


package com.soul.fileattente.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.soul.fileattente.R;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class MainActivity extends AppCompatActivity {

    private Button buttonSend;
    private TextView textResult;
    private OkHttpClient mClient;


    Request request =  null;
    EchoWebSocketListener listener = null;
    WebSocket webSocket = null;

    private final class EchoWebSocketListener extends WebSocketListener {
        private static final int CLOSE_STATUS = 1000;
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            //webSocket.send("What's up ?");
            //webSocket.send(ByteString.decodeHex("abcd"));
            //webSocket.close(CLOSE_STATUS, "Socket Closed !!");
        }
        @Override
        public void onMessage(WebSocket webSocket, String message) {
            print("Receive Message: " + message);
        }
        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            print("Receive Bytes : " + bytes.hex());
        }
        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(CLOSE_STATUS, null);
            print("Closing Socket : " + code + " / " + reason);
        }
        @Override
        public void onFailure(WebSocket webSocket, Throwable throwable, Response response) {
            print("Error : " + throwable.getMessage());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSend = (Button) findViewById(R.id.button4);
        textResult = (TextView) findViewById(R.id.textView6);
        mClient = new OkHttpClient();
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
    }
    private void start() {
        //Request request = new Request.Builder().url("ws://echo.websocket.org").build();
        //Request request = new Request.Builder().url("wss://socketsbay.com/wss/v2/1/demo/").build();
        //val websocketURL = "wss://${Constants.CLUSTER_ID}.piesocket.com/v3/1?api_key=${Constants.API_KEY}"
        //val websocketURL = "wss://${Constants.CLUSTER_ID}.piesocket.com/v3/1?api_key=${Constants.API_KEY}"

//Orig
//        Request request = new Request.Builder().url("wss://s9042.nyc1.piesocket.com/v3/1?api_key=gGbcKyjG0YlkO5UenoihzD7Bivjz7Od6JDVZCpHs&notify_self=1?api_key=gGbcKyjG0YlkO5UenoihzD7Bivjz7Od6JDVZCpHs").build();
//        EchoWebSocketListener listener = new EchoWebSocketListener();
//        WebSocket webSocket = mClient.newWebSocket(request, listener);
//        mClient.dispatcher().executorService().shutdown();

        System.out.println("--------------------------------------> request = " + request);
//        if(request == null) {
//            request = new Request.Builder().url("wss://s9042.nyc1.piesocket.com/v3/1?api_key=gGbcKyjG0YlkO5UenoihzD7Bivjz7Od6JDVZCpHs&notify_self=1?api_key=gGbcKyjG0YlkO5UenoihzD7Bivjz7Od6JDVZCpHs").build();
//            listener = new EchoWebSocketListener();
//            webSocket = mClient.newWebSocket(request, listener);
//        }

        if(request == null) {
            //request = new Request.Builder().url("wss://vps-31a4fa05.vps.ovh.net:8080").build();
            request = new Request.Builder().url("ws://vps-31a4fa05.vps.ovh.net:8080").build();
            listener = new EchoWebSocketListener();
            webSocket = mClient.newWebSocket(request, listener);
        }
        webSocket.send("This is a test...");
        //mClient.dispatcher().executorService().shutdown();
    }
    private void print(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textResult.setText(textResult.getText().toString() + "\n" + message);
            }
        });
    }
}