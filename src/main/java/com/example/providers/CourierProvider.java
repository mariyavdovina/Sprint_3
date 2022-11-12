package com.example.providers;

import com.example.models.Courier;
import io.qameta.allure.Step;

public class CourierProvider {

    @Step("Courier with default creds")
    public static Courier getDefault() {
        return new Courier("fake_login", "1234", "Bob");
    }


    @Step("Courier without login")
    public static Courier getWithPasswordOnly() {
        return new Courier("", "1234", "");
    }

    @Step("Courier without password")
    public static Courier getWithLoginOnly() {
        return new Courier("login", "", "");
    }

    @Step("Without creds")
    public static Courier getEmpty() {
        return new Courier();
    }
}
