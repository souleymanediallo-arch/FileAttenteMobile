package com.soul.fileattente.view;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.somsakelect.android.mqtt.MqttAndroidClient;
import com.soul.fileattente.databinding.ActivityNotificationsBinding;
import com.soul.fileattente.utils.Utils;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

public class MqttActivity3 extends AppCompatActivity {
    private static final String TAG = "MQTTNotificationService";
    private static final String MQTT_BROKER_URL = "tcp://37.187.90.58:1889";
    private static final String MQTT_TOPIC = "android_client_notifications_inbox_dev3";
    //private static final String MQTT_TOPIC = "android_client_inbox"; //marche meme pour une queue non persitente
    private static final int MQTT_QOS = 2;
    private static final String BASE_CLIENT_ID = "android_client_notifications_dev";
    private static final int PERMISSION_REQUEST_SMS = 1;

    private MqttAndroidClient client;
    private ActivityNotificationsBinding binding;
    private final MqttConnectOptions connectOptions = new MqttConnectOptions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initMqttOptions();
        setupListeners();
        Log.d(TAG, "Activity created");
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUI();
        Log.d(TAG, "Activity started");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disconnectClient();
        Log.d(TAG, "Activity destroyed");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_SMS && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "SMS permission granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    private void initMqttOptions() {
        connectOptions.setAutomaticReconnect(true);
        connectOptions.setCleanSession(false);
        connectOptions.setUserName("admin");
        connectOptions.setPassword("admin".toCharArray());
    }

    private void setupListeners() {
        binding.buttonStart.setOnClickListener(v -> {
            Log.d(TAG, "Start button clicked");
            connectToMqtt();
        });

        binding.buttonStop.setOnClickListener(v -> {
            Log.d(TAG, "Stop button clicked");
            disconnectClient();
        });
    }

    private void connectToMqtt() {
        String clientId = BASE_CLIENT_ID + Utils.getUniqueId(this);
        client = new MqttAndroidClient(this, MQTT_BROKER_URL, clientId);
        client.setCallback(createMqttCallback());

        try {
            updateUI(true);
            client.connect(connectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    subscribeToTopic();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    handleError(exception, "Connection failed");
                }
            });
        } catch (MqttException e) {
            handleError(e, "Connection attempt failed");
        }
    }

    private void subscribeToTopic() {
        try {
            client.subscribe(MQTT_TOPIC, MQTT_QOS);
        } catch (MqttException e) {
            handleError(e, "Subscription failed");
            updateUI(false);
        }
    }

    private MqttCallbackExtended createMqttCallback() {
        return new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                Log.d(TAG, "Connection completed to: " + serverURI + ", reconnect: " + reconnect);
                updateUI();
            }

            @Override
            public void connectionLost(Throwable cause) {
                Log.w(TAG, "Connection lost: " + cause.getMessage());
                updateUI();
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                //processIncomingMessage(message);
                Log.d(TAG, "Message arrived: " + message);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Log.d(TAG, "Message delivery completed");
            }
        };
    }

//    private void processIncomingMessage(MqttMessage message) {
//        runOnUiThread(() -> {
//            String payload = new String(message.getPayload());
//            Toast.makeText(this, payload, Toast.LENGTH_SHORT).show();
//            Log.d(TAG, "Message received: " + payload);
//
//            try {
//                JSONObject obj = new JSONObject(payload);
//                NoticationDTO notification = parseNotification(obj);
//                sendNotificationIfPermitted(notification);
//            } catch (Exception e) {
//                Log.e(TAG, "Failed to parse message: " + payload, e);
//            }
//        });
//    }

//    private NoticationDTO parseNotification(JSONObject obj) throws Exception {
//        NoticationDTO dto = new NoticationDTO();
//        dto.setIdNotification(obj.getString("idNotification"));
//        dto.setNotificationChannel(obj.getString("notificationChannel"));
//        dto.setNotificationMessage(obj.getString("notificationMessage"));
//        dto.setDestinationNumber(obj.getString("destinationNumber"));
//        dto.setNotificationStatus(obj.getString("notificationStatus"));
//        return dto;
//    }

//    private void sendNotificationIfPermitted(NoticationDTO notification) {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.SEND_SMS},
//                    PERMISSION_REQUEST_SMS);
//        } else {
//            Utils.sendTextAsSms(notification.getDestinationNumber(),
//                    notification.getNotificationMessage());
//        }
//    }

    private void disconnectClient() {
        try {
            if (client != null && client.isConnected()) {
                client.disconnect();
                updateUI(false);
            }
        } catch (MqttException e) {
            handleError(e, "Disconnection failed");
        }
    }

    private void handleError(Throwable e, String context) {
        Log.e(TAG, context + ": " + e.getMessage());
        runOnUiThread(() -> {
            binding.txtViewDesc.setText(context + ": " + e.getMessage());
            updateUI(false);
        });
    }

    private void updateUI(boolean isConnecting) {
        runOnUiThread(() -> {
            boolean isConnected = client != null && client.isConnected();
            binding.txtViewStatus.setText(isConnected || isConnecting ? "STARTED" : "STOPPED");
            binding.txtViewStatus.setTextColor(isConnected || isConnecting ?
                    Color.GREEN : Color.RED);
            if (!isConnecting) binding.txtViewDesc.setText("");
        });
    }

    private void updateUI() {
        updateUI(false);
    }
}