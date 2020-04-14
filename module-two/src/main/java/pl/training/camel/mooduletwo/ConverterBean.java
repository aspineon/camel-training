package pl.training.camel.mooduletwo;

import org.springframework.stereotype.Component;

@Component
public class ConverterBean {

    public String map(String text) {
        return text.replaceAll(";", ",");
    }

}
