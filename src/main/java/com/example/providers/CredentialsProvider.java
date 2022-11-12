package com.example.providers;

import com.example.models.Credentials;
import io.qameta.allure.Step;

public class CredentialsProvider {
    @Step("Credentials of pre-defined courier on server side")
    public static Credentials getDefault() {
        return new Credentials("login_fake", "password");//courier with given credentials already exists on server
    }

    @Step("Creds with empty login")
    public static Credentials getWithPasswordOnly() {
        return new Credentials("", "password");
    }

    @Step("Creds with empty password")
    public static Credentials getWithLoginOnly() {
        return new Credentials("login_fake", "");
    }

    @Step("Non-existing login/password pair")
    public static Credentials getInvalidPassword() {
        return new Credentials("login_fake", "pwd");
    }

}
