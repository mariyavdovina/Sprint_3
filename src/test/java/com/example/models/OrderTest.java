package com.example.models;

import com.example.clients.OrderClient;
import com.example.providers.CourierProvider;
import com.example.providers.CredentialsProvider;
import com.example.providers.OrderProvider;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CONFLICT;
import static org.junit.Assert.*;
@RunWith(Parameterized.class)
public class OrderTest {
    private Order order;
    private int statusCode;
    private Boolean containsTrack;

    private OrderClient orderClient;
    private Integer track;

    @Before
    public void setUp()  {
        orderClient =new OrderClient();
    }

    @After
    public void cleanUp()  {
        orderClient.cancel(track);
    }
    public OrderTest(Order order, int statusCode, boolean containsTrack){
        this.order=order;
        this.statusCode = statusCode;
        this.containsTrack = containsTrack;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData(){
        //List.of("")
        return new Object[][]{
                {OrderProvider.getDefault(), 201,true},
                {OrderProvider.getWithEmptyColor(), 201,true},
                {OrderProvider.getWithGREYColor(), 201,true},
                {OrderProvider.getWithTwoColors(), 201,true},
        };
    }
    @Test
    public void orderCanBeCreated(){
        ValidatableResponse responseCreate = orderClient.create(order);
        int actualCode = responseCreate.extract().statusCode();
        track = responseCreate.extract().path("track");
        boolean actualTrack = !(track.toString()).isEmpty();
        assertEquals(statusCode,actualCode);
        assertEquals(actualTrack,containsTrack);
    }

}