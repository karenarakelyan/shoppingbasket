package com.karen.shoppingbasket.services.integrationtest;

import com.karen.shoppingbasket.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Karen Arakelyan
 */
@TestPropertySource(locations = "classpath:application.properties")
@ComponentScan(basePackages = {
        "com.karen.shoppingbasket.services"
})
@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootConfiguration
@EnableAutoConfiguration
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Test
    public void test() {

    }


}
