package com.tg.manager.utils;

public class EmailValidator {
    public static boolean validatorEmail(String email){

        if(email.contains("@")){

            return true;
        }
        throw  new RuntimeException("Email invalid");
    }

    public static String validatorEmailFatec(String fatecEmail, String personEmail){
        String email = fatecEmail;
        if(fatecEmail.isEmpty()){
           return email = personEmail;
        }
        return email;
    }

}
