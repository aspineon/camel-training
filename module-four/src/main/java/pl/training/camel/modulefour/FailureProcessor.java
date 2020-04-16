package pl.training.camel.modulefour;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FailureProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {
        Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        String message = exception.getMessage();
        exchange.getIn().setHeader("Failure", message);
    }

}
