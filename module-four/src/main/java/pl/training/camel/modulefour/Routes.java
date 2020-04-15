package pl.training.camel.modulefour;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Routes extends RouteBuilder {

    @Override
    public void configure() {
        from("quartz://get?cron=0/15+*+*+*+*+?")
                .to("http://localhost:8080/greetings")
                .convertBodyTo(String.class)
                .to("log:pl.training.camel");
    }

}
