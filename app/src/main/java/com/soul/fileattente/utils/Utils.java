package com.soul.fileattente.utils;

import com.soul.fileattente.model.Param;
import com.soul.fileattente.view.GlobalSetOfExtra;

import java.util.List;

public class Utils {

    static int NBCAR = 15;
    public static String getValueForKey(List<Param> mListParams, String key){
        for(Param param : mListParams){
            if(param.getKey().equalsIgnoreCase(key)) return param.getValue();
        }
        return null;
    }

    public static String formatStringForView(String strValue){
        int len = 0;
        if(strValue != null) {
            len = strValue.length();
        }else{
            strValue = "";
        }
        StringBuilder output = new StringBuilder().append(strValue);
        if(len >= NBCAR){
            len =0;
        }else{
            len = NBCAR - len;
        }
        for(int i=0; i<len-1; i++){
            output.append(" ");
        }
        output.append(".");
        return output.toString();
    }
}


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