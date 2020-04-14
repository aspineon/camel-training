package pl.training.camel.moduleone;

import org.apache.camel.builder.RouteBuilder;

public class Routes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:module-one/source?noop=true&delay=1000").to("file:module-one/destination");
    }

}
