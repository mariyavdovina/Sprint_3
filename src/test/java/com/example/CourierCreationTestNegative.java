package com.example;

import com.example.models.Courier;
import com.example.providers.CourierProvider;
import com.example.providers.CredentialsProvider;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.example.clients.CourierClient;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CONFLICT;
import static org.junit.Assert.*;
@RunWith(Parameterized.class)
public class CourierCreationTestNegative {
    private Courier courier;
    private String message;
    private int statusCode;


    private CourierClient courierClient;
    private int id;

    @Before
    public void setUp(){
        courierClient = new CourierClient();

    }

    public CourierCreationTestNegative(Courier courier, int statusCode, String message){
        this.courier = courier;
        this.statusCode = statusCode;
        this.message = message;
    }

    //test data
    @Parameterized.Parameters
    public static Object[][] getTestData(){
        //List.of("")
        return new Object[][]{
                {CourierProvider.getWithPasswordOnly(), SC_BAD_REQUEST,"Недостаточно данных для создания учетной записи"},
                {CourierProvider.getWithLoginOnly(), SC_BAD_REQUEST,"Недостаточно данных для создания учетной записи"},
                {CourierProvider.getEmpty(), SC_BAD_REQUEST,"Недостаточно данных для создания учетной записи"},
                {new Courier(CredentialsProvider.getDefault().getLogin(),CredentialsProvider.getDefault().getPassword(),""), SC_CONFLICT,"Этот логин уже используется. Попробуйте другой."},
                {new Courier(CredentialsProvider.getDefault().getLogin(),"any",""), SC_CONFLICT,"Этот логин уже используется. Попробуйте другой."},
        };
    }

    @Test
    public void courierCanBeCreated(){
        //courier.setPassword("");
        ValidatableResponse responseCreate = courierClient.create(courier);
        String actualMessage = responseCreate.extract().path("message");
        int actualStatusCode = responseCreate.extract().path("code");
        assertEquals(statusCode, actualStatusCode);
        assertEquals(message, actualMessage);
    }

}