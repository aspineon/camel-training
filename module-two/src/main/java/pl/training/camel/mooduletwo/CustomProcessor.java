package pl.training.camel.mooduletwo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CustomProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {
        String text = exchange.getIn().getBody(String.class);
        exchange.getIn().setBody(text.replaceAll(";", ","));
    }

}
