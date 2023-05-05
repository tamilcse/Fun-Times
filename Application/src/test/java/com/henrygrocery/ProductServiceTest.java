package com.henrygrocery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.henrysgrocery.model.Product;
import com.henrysgrocery.service.ProductService;

public class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    public void setup() {
        productService = new ProductService();
        productService.addProduct(new Product("soup","tin", 0.65));
        productService.addProduct(new Product("bread","loaf", 0.80));
        productService.addProduct(new Product("apples","single", 0.10));
        productService.addProduct(new Product("milk", "bottle", 1.15));
    }

    @Test
    public void testGetProductByName() {
        Product productA = productService.getProductByName("soup");
        Product productB = productService.getProductByName("bread");
        Product productC = productService.getProductByName("apples");
        Product productD = productService.getProductByName("milk");

        Assertions.assertNotNull(productA);
        Assertions.assertEquals("soup", productA.getName());
        Assertions.assertEquals(0.65, productA.getCost());

        Assertions.assertNotNull(productB);
        Assertions.assertEquals("bread", productB.getName());
        Assertions.assertEquals(0.80, productB.getCost());

        Assertions.assertNotNull(productC);
        Assertions.assertEquals("apples", productC.getName());
        Assertions.assertEquals(0.10, productC.getCost());
        
        Assertions.assertNotNull(productC);
        Assertions.assertEquals("milk", productD.getName());
        Assertions.assertEquals(1.15, productD.getCost());

        Product productNull = productService.getProductByName("Product D");
        Assertions.assertNull(productNull);
    }

}
