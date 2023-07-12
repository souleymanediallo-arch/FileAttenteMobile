//Info o the testing online
//https://www.piesocket.com/websocket-tester?url=wss%3A%2F%2Fs9042.nyc1.piesocket.com%2Fv3%2F1%3Fapi_key%3DgGbcKyjG0YlkO5UenoihzD7Bivjz7Od6JDVZCpHs%26notify_self%3D1
//Testing WebSocket for Android
//https://www.piesocket.com/app/v4/onboarding
//d_souleymane@yahoo.com
//P@sswordPs100
//valanle jusqu'au '27 Juin 2023


package com.soul.fileattente.view;

import static com.soul.fileattente.utils.ApplicationConstants.*;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.soul.fileattente.R;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class MainActivity extends AppCompatActivity {

    MqttAndroidClient client;
    int message_number = 0;

    private Button buttonSend;
    private Button buttonConnect;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        buttonSend = (Button) findViewById(R.id.button4);
        buttonConnect = (Button) findViewById(R.id.button5);
        textResult = (TextView) findViewById(R.id.textView6);

        buttonSend.setEnabled(false);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //buttonSend.setEnabled(false);
                publishMessage("From Android Client....." + message_number++);
            }
        });

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connect();
            }
        });

    }

    private void print(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textResult.setText(textResult.getText().toString() + "\n" + message);
            }
        });
    }


    private void connect() {
        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setAutomaticReconnect(true);

        client = new MqttAndroidClient(this, serverURI, clientId);
        try {
            client.connect(connectOptions, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
//                    System.out.println("client ------------------------------------------------> " + client.toString());
//                    if(client == null){
//                        System.out.println("client null------------------------------------------------> ");
//                    }else{
//                        System.out.println("client NOT null------------------------------------------------> ");
//                        //int count = client.getBufferedMessage(0);
//                        MqttMessage current_message = client.getBufferedMessage(0);
//                        System.out.println(current_message.toString());
//                    }
//                    int count = client.getBufferedMessageCount();
//                    System.out.println("client.getBufferedMessageCount() ------------------> " + count);
//                    MqttMessage current_message;
//                    for(int i=0; i<count; i++){
//                        current_message = client.getBufferedMessage(i);
//                        System.out.println("client.getBufferedMessage("+i+")------------------> " + current_message.toString());
//                    }
                    //----
                    buttonSend.setEnabled(true);
                    subscribe();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable e) {
                    e.printStackTrace();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void subscribe() {
        try {

            client.subscribe(subscribeTopic, 0, new IMqttMessageListener() {
                @Override
                public void messageArrived(final String topic, final MqttMessage message) throws Exception {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, message.toString(), Toast.LENGTH_SHORT).show();
                            System.out.println("subscribe Incoming Message --------------------------------------------------------------------->" + message.toString());
                            print(message.toString());
                        }
                    });
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

//        int count = client.getBufferedMessageCount();
//        System.out.println("client.getBufferedMessageCount() ------------------> " + count);
//        MqttMessage current_message;
//        for(int i=0; i<count; i++){
//            current_message = client.getBufferedMessage(i);
//            System.out.println("client.getBufferedMessage("+i+")------------------> " + current_message.toString());
//        }

    }

    private void publishMessage(String message) {
        MqttMessage msg = new MqttMessage();
        msg.setPayload(message.getBytes());
        try {
            client.publish(publishTopic, msg);
            System.out.println("publishMessage Outgoing Message --------------------------------------------------------------------->" + message);
            print(message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}