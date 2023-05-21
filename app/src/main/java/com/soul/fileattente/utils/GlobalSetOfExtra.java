package com.soul.fileattente.utils;

import com.soul.fileattente.model.AutheticationResult;
import com.soul.fileattente.model.Login;
import com.soul.fileattente.model.LoginResult;
import com.soul.fileattente.model.Param;
import com.soul.fileattente.model.ServiceDestination;

import java.io.Serializable;
import java.util.List;

public class GlobalSetOfExtra implements Serializable {
    public Login mLogin;
    public AutheticationResult mAuthenticationResult;
    public LoginResult mLoginResult;
    public List<Param> mListParams;
    public ServiceDestination mServiceDestination;

    public static final String GLOBALSETOFEXTRA = "global_set_of_extra";
    public static final String PROVIDED_TELEPHONE_NUMBER_KEY = "provided_telephone_number";
    public static final String GENERATED_NUMNER_FOR_CURRENT_SERVICE_KEY = "generated_number";
    public static int VALUE_GENERATED_NUMERO = 0;
}
