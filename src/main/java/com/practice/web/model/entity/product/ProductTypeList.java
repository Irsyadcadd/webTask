package com.practice.web.model.entity.product;

import java.util.Arrays;
import java.util.List;

public class ProductTypeList {
    public static List<String> getProductTypes() {
        return Arrays.asList(
            "Cleaning Tools", "Electronics", "Furniture", "Clothing",
            "Food", "Beverage", "Books", "Toys", "Beauty Products", "Office Supplies"
        );
    }
}
