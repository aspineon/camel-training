package pl.training.camel.moduleone;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

public class Example4 {

    public static void main(String[] args) throws InterruptedException {
        try (ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml")) {
            Thread.sleep(10_000);
        }
    }

}
