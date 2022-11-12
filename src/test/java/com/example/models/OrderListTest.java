package com.example.models;

import com.example.clients.OrderClient;
import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderListTest {
    private OrderClient orderClient;
    @Before
    public void setUp()  {
        orderClient =new OrderClient();
    }

    @Test
    @Description("Checking that list of orders returns")
    public void getListOfOrders(){
        ValidatableResponse responseCreate = orderClient.listOfOrders();
        responseCreate.assertThat().statusCode(200);
        assertNotNull(responseCreate.extract().path("orders"));

    }

}