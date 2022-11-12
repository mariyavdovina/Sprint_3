package com.example;

import com.example.clients.CourierClient;
import com.example.models.Credentials;
import com.example.providers.CredentialsProvider;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CourierLoginTestNegative {
    private Credentials credentials;
    private String message;
    private int statusCode;


    private CourierClient courierClient;
    private int id;

    @Before
    public void setUp(){
        courierClient = new CourierClient();

    }

    public CourierLoginTestNegative(Credentials credentials, int statusCode, String message){
        this.credentials = credentials;
        this.statusCode = statusCode;
        this.message = message;
    }

    //test data
    @Parameterized.Parameters
    public static Object[][] getTestData(){
        //List.of("")
        return new Object[][]{
                {CredentialsProvider.getWithPasswordOnly(), SC_BAD_REQUEST,"Недостаточно данных для входа"},
                {CredentialsProvider.getWithLoginOnly(), SC_BAD_REQUEST,"Недостаточно данных для входа"},
                {CredentialsProvider.getInvalidPassword(), SC_NOT_FOUND,"Учетная запись не найдена"},
        };
    }

    @Test
    public void courierCanLogin(){
        ValidatableResponse responseLogin = courierClient.login(credentials);
        String actualMessage = responseLogin.extract().path("message");
        int actualStatusCode = responseLogin.extract().path("code");
        assertEquals(statusCode, actualStatusCode);
        assertEquals(message, actualMessage);
    }
}
