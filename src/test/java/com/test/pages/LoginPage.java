package com.test.pages;

public class LoginPage implements PageObject {

    public String URL(){
        return "http://localhost:9949/auth-login-stub/gg-sign-in";
     }
    public String TITLE(){
        return "";
    }
    public String HEADING(){
        return "";
    }

    public static String CRED_ID_FIELD = "authorityId";
    public static String REDIRECT_URL_FIELD = "redirectionUrl";
    public static String REDIRECT_URL_PREFIX = "http://localhost:9232/personal-account/do-uplift?redirectUrl=";
    public static String CONFIDENCE_LEVEL_FIELD = "confidenceLevel";
    public static String NINO_FIELD = "nino";
    public static String URL_ID = "http://localhost:9949/auth-login-stub/gg-sign-in";

}
