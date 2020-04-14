package pl.training.camel.mooduletwo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan("pl.training.camel")
@ImportResource("classpath:camel.xml")
public class Application {

    public static void main(String[] args) throws InterruptedException {
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class)) {
            Thread.sleep(10_000);
        }
    }

}
