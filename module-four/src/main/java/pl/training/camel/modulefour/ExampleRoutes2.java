package pl.training.camel.modulefour;

import org.apache.camel.LoggingLevel;
import org.apache.camel.ValidationException;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ExampleRoutes2 extends BaseRoutes {

    @Override
    public void configure() throws Exception {
        /*errorHandler(defaultErrorHandler()
                    //.redeliveryDelay(1000)
                    .retryAttemptedLogLevel(LoggingLevel.INFO)
                    .useExponentialBackOff()
                    .backOffMultiplier(2)
                    .maximumRedeliveries(5));*/

        /*errorHandler(deadLetterChannel("log:dead?level=ERROR")
            .useOriginalMessage()
            .onPrepareFailure(new FailureProcessor())
        );

        onException(RuntimeException.class, ValidationException.class).maximumRedeliveries(3);



        from("file:module-four/source?noop=true")
                //.onException(RuntimeException.class, ValidationException.class).maximumRedeliveries(3)
                .onException(IllegalArgumentException.class)
                    .continued(true)

                .convertBodyTo(String.class)
                .doTry()
                    .bean("upperCase")
                    .bean("lowerCase")
                .doCatch(IllegalAccessException.class)
                    .process(new FailureProcessor())
                    .to("log:pl.training.camel")
                .end()
                .to("log:pl.training.camel");*/
    }

}
