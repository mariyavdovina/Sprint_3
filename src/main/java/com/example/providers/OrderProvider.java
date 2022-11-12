package com.example.providers;

import com.example.models.Order;
import io.qameta.allure.Step;

import java.util.List;

import static com.example.models.Colour.BLACK;
import static com.example.models.Colour.GREY;

public class OrderProvider {
    @Step("Default order without colour")
    public static Order getDefault() {
        return new Order();
    }

    @Step("Default order with empty colour")
    public static Order getWithEmptyColor() {
        return new Order(List.of(""));
    }

    @Step("Default order with one colour")
    public static Order getWithGREYColor() {
        return new Order(List.of(GREY));
    }

    @Step("Default order with two colours")
    public static Order getWithTwoColors() {
        return new Order(List.of(GREY, BLACK));
    }

}
