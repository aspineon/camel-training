package pl.training.camel.moduleone;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class Example1 {

    public static void main(String[] args) throws Exception {
        Routes routes = new Routes();
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(routes);
        camelContext.start();
        Thread.sleep(10_000);
        camelContext.stop();
    }

}
