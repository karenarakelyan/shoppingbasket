package com.karen.shoppingbasket;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Karen Arakelyan
 */

@SpringBootConfiguration
@EnableJpaRepositories
@ComponentScan(value = "com.karen.shoppingbasket.repository")
public class Configuration {

}
