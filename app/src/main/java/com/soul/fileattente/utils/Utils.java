//https://healthicons.org/ //Pour les icones Medicales
package com.soul.fileattente.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import com.soul.fileattente.R;
import java.util.List;
import java.util.Locale;

public class Utils {

    static int NBCAR = 15;

    public static String formatStringForView(String strValue) {
        int len = 0;
        if (strValue != null) {
            len = strValue.length();
        } else {
            strValue = "";
        }
        StringBuilder output = new StringBuilder().append(strValue);
        if (len >= NBCAR) {
            len = 0;
        } else {
            len = NBCAR - len;
        }
        for (int i = 0; i < len - 1; i++) {
            output.append(" ");
        }
        output.append(".");
        return output.toString();
    }


    public static void translateFromTextToSpeech(TextToSpeech initializedTextToSpeechInstancefromCallingActivity, String textToRenderOverVoice) {
        initializedTextToSpeechInstancefromCallingActivity.speak(textToRenderOverVoice, TextToSpeech.QUEUE_FLUSH, null);
    }

    public static void sendTextAsSms(String mobileNumber, String textMessage){
        if(mobileNumber.equalsIgnoreCase("000000000")) {
            mobileNumber="+33652178684";
        }
        SmsManager smsManager = SmsManager.getDefault();
        if(smsManager != null) {
            smsManager.sendTextMessage(mobileNumber, null, textMessage, null, null);
        }
    }

    //https://www.tutlane.com/tutorial/android/android-send-sms-with-examples
    public static void sendTextAsSms2(Context context, String mobileNumber, String textMessage) {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("smsto:"));
            i.setType("vnd.android-dir/mms-sms");
            i.putExtra("address", mobileNumber);
            i.putExtra("sms_body", textMessage);
            context.startActivity(Intent.createChooser(i, "Send sms via:"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            Toast.makeText(context, "Echec envoi sms...", Toast.LENGTH_SHORT).show();
        }
    }

    //https://www.tutorialspoint.com/android/android_sending_email.htm
    public static void sendTextAsEmail(Context context, String[] to, String[] cc, String subject, String message) {
        Log.i("Send email", "");
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            //finish();
            Toast.makeText(context, "Finished sending email...", Toast.LENGTH_SHORT).show();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    //https://www.geeksforgeeks.org/how-to-send-message-on-whatsapp-in-android/
    public static void sendTextAsWhatsAppMessage(Context context, String message) {
        // Creating new intent
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");
        // Give your message here
        intent.putExtra(Intent.EXTRA_TEXT, message);
        // Checking whether Whatsapp is installed or not
        if (intent.resolveActivity(context.getPackageManager()) == null) {
            Toast.makeText(context, "Please install whatsapp first.", Toast.LENGTH_SHORT).show();
            return;
        }
        // Starting Whatsapp
        context.startActivity(intent);
    }

    //https://www.vogella.com/tutorials/AndroidIntent/article.html
    public static void openWhatsApp(Context context, View view) {
        try {
            String text = "This is a test";// Replace with your message.
            String toNumber = "xxxxxxxxxx"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
            //Suppose your country is Senegal and your phone number is “xxxxxxxxxx”, then you need to send “221xxxxxxxxxx”.
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void placeAPhoneCall(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }

    public static int getRihtImageIdGivenServiceName(String serviceName){

//        return R.drawable.ic_medecine;
        switch(serviceName){
            case "Medecine":
                return R.drawable.ic_medecine;
            case "Diabetologie":
                return R.drawable.ic_diabetologie;
            case "Gynecologie":
                return R.drawable.ic_gynecologie;
            case "Pediatrie":
                return R.drawable.ic_pediatrie;
            case "Chirurgie":
                return R.drawable.ic_chirurgie;
            case "Analyse (Biologie)":
                return R.drawable.ic_analyse_biologie;
            case "Retrait Analyses":
                return R.drawable.ic_retraitanalyses;
            case "Orthopedie":
                return R.drawable.ic_orthopedie;
            case "Neurologie":
                return R.drawable.ic_neurologie;
            case "Neurochirurgie":
                return R.drawable.ic_neurochirurgie;
            case "Ophtalmologie":
                return R.drawable.ic_ophtalmologie;
            case "Dermatologie":
                return R.drawable.ic_kinesitherapie;
            case "Psychiatrie":
                return R.drawable.ic_psychiatrie;
            case "Psychologie":
                return R.drawable.ic_psychologie;
            case "Kinesitherapie":
                return R.drawable.ic_kinesitherapie;
            case "Rhumatologie":
                return R.drawable.ic_rhumatologie;
            case "Radiologie -Scanner":
                return R.drawable.ic_radiologie__scanner;
            case "Chirurgie Pediatrie":
                return R.drawable.ic_chirurgie_pediatrie;
            case "Cardiologie":
                return R.drawable.ic_cardiologie;
            case "Gastrologie":
                return R.drawable.ic_gastrologie;
            case "Nephrologie":
                return R.drawable.ic_nephrologie;
            case "Pneumologie":
                return R.drawable.ic_pneumologie;
            case "Urologie":
                return R.drawable.ic_urologie;
            case "ORL":
                return R.drawable.ic_orl;
            case "Anesthesie":
                return R.drawable.ic_anesthesie;
            case "Dieteticien":
                return R.drawable.ic_dieteticien;
            case "Hematologie":
                return R.drawable.ic_hematologie;
            case "Echographie":
                return R.drawable.ic_echographie;
            default:
                return R.drawable.ic_patient_band;
        }
    }

    public static String formatSenegalTelephoneNumberForTextToVoice(String telephoneNumber){
        String formattedTelephoneNumber = "";
        if(telephoneNumber == null) return "00 000 00 00";

        if(telephoneNumber.length() == 12) { //221766752276
            formattedTelephoneNumber = "+" + telephoneNumber.substring(0, 3);
            telephoneNumber = telephoneNumber.substring(3, 12);
        }
        if(telephoneNumber.length() == 13) { //+221766752276
            formattedTelephoneNumber = telephoneNumber.substring(0, 4);
            telephoneNumber = telephoneNumber.substring(4, 13);
        }
        if(telephoneNumber.length() == 14) { //00221766752276
            formattedTelephoneNumber = telephoneNumber.substring(0, 5);
            telephoneNumber = telephoneNumber.substring(5, 14);
        }
        if(telephoneNumber.length() == 9) { //766752276
            formattedTelephoneNumber = formattedTelephoneNumber + " " + telephoneNumber.substring(0, 2);
            formattedTelephoneNumber = formattedTelephoneNumber + " " + telephoneNumber.substring(2, 5);
            formattedTelephoneNumber = formattedTelephoneNumber + " " + telephoneNumber.substring(5, 7);
            formattedTelephoneNumber = formattedTelephoneNumber + " " + telephoneNumber.substring(7, 9);
        }
        return formattedTelephoneNumber;
    }


    public static String formatNumeroDemandeurForTextToVoice(String numeroDemandeur){

        if(numeroDemandeur == null || numeroDemandeur.length()<=3)  return "";
        int len = numeroDemandeur.length();
        StringBuilder prefixNumero = new StringBuilder();
        StringBuilder suffixNumero = new StringBuilder();
        for(int i=0; i<3; i++){
            prefixNumero.append(numeroDemandeur.charAt(i)).append(" ");
        }
        for(int i=3; i<len; i++){
            suffixNumero.append(numeroDemandeur.charAt(i));
        }
        if(suffixNumero.toString().startsWith("0")){
            String strSuffix = suffixNumero.toString();
            len = strSuffix.length();
            suffixNumero = new StringBuilder();
            for(int i=0; i<len; i++){
                suffixNumero.append(strSuffix.charAt(i)).append(" ");
            }
        }
        return prefixNumero.toString() + " " + suffixNumero.toString();
    }

    public static String getUniqueId(Context context){
        String android_device_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return "_" + android_device_id;
    }
}