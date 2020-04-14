package pl.training.camel.mooduletwo;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static org.apache.camel.support.builder.PredicateBuilder.not;

@Component
public class Example1 extends RouteBuilder {

    @Override
    public void configure() {
        from("file:module-two/source?noop=true&delay=5000")
                .filter(not(header("CamelFileName").endsWith("json")))
                .filter(xpath("/order[not(@test)]"))
                .log(LoggingLevel.INFO, "Received file: ${header.CamelFileName}")
                .to("jms:placedOrders");

        from("jms:placedOrders")
                .choice()
                    .when(header("CamelFileName").endsWith(".xml"))
                        .to("jms:xmlOrders")
                    .when(header("CamelFileName").regex("^.*csv$"))
                        .to("jms:cvsOrders")
                    .otherwise()
                        .to("jms:invalidOrders")
                .end()
                .to("jms:processing");

        from("jms:xmlOrders").to("log:pl.training.camel");
        from("jms:cvsOrders").to("log:pl.training.camel");
        from("jms:invalidOrders").to("log:pl.training.camel");

        /*from("jms:processing")
                .multicast()
                .parallelProcessing()
                .to("jms:accounting", "jms:hr");*/

        from("jms:processing")
                //.bean(Recipients.class)
                //.setHeader("recipients", () -> "jms:accounting")
                .setHeader("recipients", method(Recipients.class, "accounting"))
                .recipientList(header("recipients"));

        from("jms:accounting").to("log:pl.training.camel");
        from("jms:hr").to("log:pl.training.camel");
    }

}
