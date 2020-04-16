package pl.training.camel.modulefour;

import org.springframework.stereotype.Component;

@Component
public class LowerCase {

    public String map(String text) {
       // return text.toLowerCase();
        throw new RuntimeException();
    }

}
