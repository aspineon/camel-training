package pl.training.camel.mooduletwo;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Example1 extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:module-two/source?noop=true&delay=5000")
                .filter(xpath("/order[not(@test)]"))
                .log(LoggingLevel.INFO, "Received file: ${header.CamelFileName}")
                .to("file:module-two/destination");
    }

}
