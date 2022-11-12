package com.example;

import com.example.models.Courier;
import com.example.models.Credentials;
import com.example.providers.CourierProvider;
import com.example.providers.CredentialsProvider;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.example.clients.CourierClient;

import static org.hamcrest.Matchers.equalTo;

public class CourierTest {

    private CourierClient courierClient;
    private Courier courier;
    private int id;
    private Credentials credentials;

    @Before
    public void setUp(){
        courierClient = new CourierClient();
        courier = CourierProvider.getDefault();
        credentials = CredentialsProvider.getDefault();

    }
    @After
    public void cleanUp(){
        courierClient.delete(id);
    }

    @Test
    public void courierCanBeCreated(){
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        boolean isCourierCreated = responseCreate.extract().path("ok");
        int statusCode = responseCreate.extract().statusCode();
        System.out.println(statusCode);
        responseCreate.assertThat().body("ok", equalTo(true));
        responseCreate.assertThat().statusCode(201);
        responseLogin.assertThat().statusCode(200);
    }

    @Test
    public void courierCanLogin(){
        ValidatableResponse responseLogin = courierClient.login(credentials);
        int statusCode = responseLogin.extract().statusCode();
        Assert.assertEquals(200, statusCode);
    }

}