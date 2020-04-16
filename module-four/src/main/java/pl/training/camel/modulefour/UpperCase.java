package pl.training.camel.modulefour;

import org.springframework.stereotype.Component;

@Component
public class UpperCase {

    public String map(String text) {
        return text.toUpperCase();
    }

}
