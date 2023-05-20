package com.soul.fileattente.utils;

import com.soul.fileattente.model.Param;
import com.soul.fileattente.view.GlobalSetOfExtra;

import java.util.List;

public class Utils {

    public static String getValueForKey(List<Param> mListParams, String key){
        for(Param param : mListParams){
            if(param.getKey().equalsIgnoreCase(key)) return param.getValue();
        }
        return null;
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