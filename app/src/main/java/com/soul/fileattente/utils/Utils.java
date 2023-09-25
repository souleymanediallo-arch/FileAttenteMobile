//https://healthicons.org/ //Pour les icones Medicales
package com.soul.fileattente.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.soul.fileattente.R;
import com.soul.fileattente.model.Param;

import java.util.List;
import java.util.Locale;

public class Utils {

    static int NBCAR = 15;

    public static String getValueForKey(List<Param> mListParams, String key) {
        for (Param param : mListParams) {
            if (param.getKey().equalsIgnoreCase(key)) return param.getValue();
        }
        return null;
    }

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

        ////For immediate Play of Single Message
        initializedTextToSpeechInstancefromCallingActivity.speak(textToRenderOverVoice, TextToSpeech.QUEUE_FLUSH, null);
        //Queing messages and read them one after the other
        //initializedTextToSpeechInstancefromCallingActivity.speak(textToRenderOverVoice, TextToSpeech.QUEUE_ADD, null);
        //Sample
        //for (int i = 511; i <= 516; i++) {
        //    initializedTextToSpeechInstancefromCallingActivity.speak("Service Gynecologie, Numero " + i + " c'est à vous...", TextToSpeech.QUEUE_ADD, null);
        //}
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

//    //https://www.tutlane.com/tutorial/android/android-send-sms-with-examples
//    public static void sendTextAsSms(Context context, String mobileNumber, String textMessage) {
//        try {
//            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse("smsto:"));
//            i.setType("vnd.android-dir/mms-sms");
//            i.putExtra("address", mobileNumber);
//            i.putExtra("sms_body", textMessage);
//            context.startActivity(Intent.createChooser(i, "Send sms via:"));
//        } catch (Exception e) {
//            System.out.println(e.getLocalizedMessage());
//            Toast.makeText(context, "Echec envoi sms...", Toast.LENGTH_SHORT).show();
//        }
//    }

    //https://www.tutorialspoint.com/android/android_sending_email.htm
    public static void sendTextAsEmail(Context context, String[] to, String[] cc, String subject, String message) {
        //protected void sendTextAsEmail() {
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

        // Checking whether Whatsapp
        // is installed or not
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
            //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.
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

//    public static int getRihtImageIdGivenServiceName(String serviceName){
//
//        switch(serviceName){
//
//            case "Pediatrie":
//                return R.drawable.ic_child_care;
//
//            case "Gynecologie":
//                return R.drawable.ic_pregnant_woman;
//
//            case "Chirurgie":
//                return R.drawable.ic_airline_bed_surgery;
//
//            case "Laboratoire":
//                return R.drawable.ic_lab;
//
//            case "Retrait Analyses":
//                return R.drawable.ic_retrait_res;
//
//            case "Radiologie -Scanner":
//                return R.drawable.ic_radio;
//
//            case "Scanner":
//                return R.drawable.ic_scanner;
//
//            case "Medecine"://Generaliste
//                return R.drawable.ic_stetoscope_generaliste;
//
//            case "Cardiologie":
//                return R.drawable.ic_cardiologie;
//
//            case "Analyse (Biologie)":
//                return R.drawable.ic_analyse;
//            default:
//                return R.drawable.ic_baseline_timer_24;
//        }
//    }

    public static int getRihtImageIdGivenServiceName(String serviceName){

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

            case "Orthopedie"://Generaliste
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

            case "Chirurgie Pediatrie"://Generaliste
                return R.drawable.ic_chirurgie_pediatrie;

            case "Cardiologie":
                return R.drawable.ic_cardiologie;

            case "Gastrologie":
                return R.drawable.ic_gastrologie;

            case "Nephrologie":
                return R.drawable.ic_nephrologie;

            case "Pneumologie":
                return R.drawable.ic_pneumologie;

            case "Urologie"://Generaliste
                return R.drawable.ic_urologie;

            case "ORL":
                return R.drawable.ic_orl;

            case "Anesthesie":
                return R.drawable.ic_anesthesie;

            case "Dieteticien"://Generaliste
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

        if(numeroDemandeur == null) return "";
        String formattedNumeroDemandeur = "";
        int len = numeroDemandeur.length();
        StringBuilder aStringBuilder = new StringBuilder();
        for(int i=0; i<len; i++){
            aStringBuilder.append(numeroDemandeur.charAt(i)).append(" ");
            //aStringBuilder.append(numeroDemandeur.charAt(i)).append(";");
        }
        return aStringBuilder.toString();
    }


    //Better Place this in the activity needing them because probably of the Contexte
    //-------------------------------------------------------------------------------
    //

    //------------------------------------------------------------------------------------

//    TextToSpeech  initializedTextToSpeechInstancefromCallingActivity;
//    // create an object textToSpeech and adding features into it
//    void initializedTextToSpeechInstance(){
//        initializedTextToSpeechInstancefromCallingActivity = new TextToSpeech(this.getApplicationContext(), new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int i) {
//                if(i!=TextToSpeech.ERROR){
//                    initializedTextToSpeechInstancefromCallingActivity.setLanguage(Locale.FRANCE);
//                    //initializedTextToSpeechInstancefromCallingActivity.speak("Hello Soulyemane..", TextToSpeech.QUEUE_FLUSH, null);
//                }
//            }
//        });
//    }

//    //https://www.tutlane.com/tutorial/android/android-send-sms-with-examples
//    void sendTextAsSms(String mobileNumber, String textMessage) {
//        try{
//            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse("smsto:"));
//            i.setType("vnd.android-dir/mms-sms");
//            i.putExtra("address", mobileNumber);
//            i.putExtra("sms_body",textMessage);
//            startActivity(Intent.createChooser(i, "Send sms via:"));
//        }
//        catch(Exception e){
//            Toast.makeText(this, "Echec envoi sms...", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    //https://www.tutorialspoint.com/android/android_sending_email.htm
//    protected void sendTextAsEmail(String[] to, String[] cc, String  subject, String message) {
//    //protected void sendTextAsEmail() {
//        Log.i("Send email", "");
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//
//        emailIntent.setData(Uri.parse("mailto:"));
//        emailIntent.setType("text/plain");
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
//        emailIntent.putExtra(Intent.EXTRA_CC, cc);
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
//        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
//
//        try {
//            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
//            finish();
//            Toast.makeText(this, "Finished sending email...", Toast.LENGTH_SHORT).show();
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    //https://www.geeksforgeeks.org/how-to-send-message-on-whatsapp-in-android/
//    private void sendTextAsWhatsAppMessage(String message)
//    {
//        // Creating new intent
//        Intent intent = new Intent(Intent.ACTION_SEND);
//
//        intent.setType("text/plain");
//        intent.setPackage("com.whatsapp");
//
//        // Give your message here
//        intent.putExtra(Intent.EXTRA_TEXT, message);
//
//        // Checking whether Whatsapp
//        // is installed or not
//        if (intent.resolveActivity(getPackageManager()) == null) {
//            Toast.makeText(this,"Please install whatsapp first.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        // Starting Whatsapp
//        startActivity(intent);
//    }
//
//    //https://www.vogella.com/tutorials/AndroidIntent/article.html
//    public void openWhatsApp(View view){
//        try {
//            String text = "This is a test";// Replace with your message.
//
//            String toNumber = "xxxxxxxxxx"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
//            //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
//            startActivity(intent);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    private void placeAPhoneCall(String phoneNumber) {
//        Intent intent = new Intent(Intent.ACTION_CALL);
//        intent.setData(Uri.parse("tel:" + phoneNumber));
//        this.startActivity(intent);
//    }
    //-------------------------------------------------------------------------------
    //Better Place this in the activity needing them because probably of the Contexte

    //Sample Usage or Call See in the Previos version Number
    //    void processTaskWhenloginButtonClicked() { // Keep this for sample usage
//        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("-------------------------------------------------------------> I got clicked");
//                //userViewModel.getAllbirthdays(); //Ajust with necassayr modications for BaseURL and so on...
//                userViewModel.authenticate(new Login("admin", "admin"));
//                userViewModel.login(new Login("docteur", "Passworddocteur"));
//                userViewModel.demandeAllParams(new DemandeParam("Vision Medicale Coumba", "0122455789632111441251", "2023-05-13T10:35:02.678Z"));
//                //userViewModel.demandeAllServicesDestination(new DemandeService("Vision Medicale Coumba","0122455789632111441251","2023-05-13T10:35:02.678Z"));
//                //userViewModel.demandeNumerosSuivant(new DemandeNumeroFile("Vision Medicale Coumba", "0122455789632111441251", "Pediatrie", "+221766752276", "2023-05-13T10:35:02.678Z" ));
//                //userViewModel.getAllNumerosSuivants();
//                //userViewModel.appelerNumero(new NumeroSuivantFile("0001","2023-05-13T10:36:02.678Z","100", "300", "250", "Vision Medicale Coumba", "0122455789632111441251","Pediatrie","+221766752276", "2023-05-13T10:35:02.678Z" ));
//                //userViewModel.annulerAppelNumero(new NumeroSuivantFile("0001","2023-05-13T10:36:02.678Z","100", "300", "250", "Vision Medicale Coumba", "0122455789632111441251","Pediatrie","+221766752276", "2023-05-13T10:35:02.678Z" ));
//                //userViewModel.demandeAllNumerosSuivants(new DemandeNumSuiv("Vision Medicale Coumba","Pediatrie","0122455789632111441251","2023-05-13T10:35:02.678Z"));
//            }
//        });
//    }
    //    ------------------------------------------------------------------------------------
    //https://www.androidauthority.com/install-ubuntu-on-your-android-smartphone-765408/
    //https://medium.com/junior-dev/how-to-re-purpose-your-old-android-phone-by-running-linux-on-it-1310df46b3fe
    //https://linuxhint.com/ubuntu-for-android/
    //    ------------------------------------------------------------------------------------

//[
//        {
//        "key": "messageBienvenue",
//        "value": "Bienvenue à la clinique Vision Medicale Coumba"
//        },
//        {
//        "key": "messageChoixService",
//        "value": "Veuillez Choisir le service de destinantion"
//        },
//        {
//        "key": "messageinviteSaisieNumeroTel",
//        "value": "Veuillez saisir votre numero de Telephone"
//        },
//        {
//        "key": "+indicatifPays",
//        "value": "+221"
//        },
//        {
//        "key": "langue",
//        "value": "fr"
//        },
//        {
//        "key": "messageIndicatifNumeroService",
//        "value": "Votre numero pour le Service [serviceChoisi] est :"
//        },
//        {
//        "key": "messageSmsEnvoye",
//        "value": "notification envoyée par sms au [numeroTelephne]"
//        },
//        {
//        "key": "messageEmailEnvoye",
//        "value": "notification envoyée par mail au [adresseEmai]"
//        },
//        {
//        "key": "messageWhatsAppEnvoye",
//        "value": "notification envoyée par whatsApp au [numeroTelephne]"
//        },
//        {
//        "key": "messageBluetoothEnvoye",
//        "value": "notification envoyée par Bluetooth au [numeroTelephne]"
//        },
//        {
//        "key": "tempsAttenteRetourAutomatiqueEcranService",
//        "value": "10"
//        },
//        {
//        "key": "lireinviteAppelNumeroSuivant",
//        "value": "false"
//        }
//]
}