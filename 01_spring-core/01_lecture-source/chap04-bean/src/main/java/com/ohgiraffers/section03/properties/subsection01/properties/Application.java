package com.ohgiraffers.section03.properties.subsection01.properties;

import com.ohgiraffers.common.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);

        Product carpBread = context.getBean("carpBread",Product.class);

        Product milk = context.getBean("milk",Product.class);

        Product water = context.getBean("water",Product.class);

        System.out.println("carpBread = "+carpBread);
        System.out.println("milk = "+milk);
        System.out.println("water = "+water);

    }
}
