package pl.training.camel.modulefour;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class BaseRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        errorHandler(defaultErrorHandler()
                //.redeliveryDelay(1000)
                .retryAttemptedLogLevel(LoggingLevel.INFO)
                .useExponentialBackOff()
                .backOffMultiplier(2)
                .maximumRedeliveries(5));
    }

}
