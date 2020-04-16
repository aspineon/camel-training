package pl.training.camel.modulefour;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ExampleRoutes1 extends RouteBuilder {

    @Override
    public void configure() {
        from("file://module-four/target/source")
                .to("file://module-four/target/destination");

        from("stub:jms:topic:training")
                .to("mock:training");
    }

}
