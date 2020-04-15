package pl.training.camel.mooduletwo;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static org.apache.camel.support.builder.PredicateBuilder.not;

@Component
public class Example1 extends RouteBuilder {

    @Override
    public void configure() {
        /*from("file:module-two/source?noop=true&delay=5000")
                .filter(not(header("CamelFileName").endsWith("json")))
                .filter(xpath("/order[not(@test)]"))
                .log(LoggingLevel.INFO, "Received file: ${header.CamelFileName}")
                .to("jms:placedOrders");

        // content based router

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

        // multicasting

        from("jms:processing")
                .multicast()
                .parallelProcessing()
                .to("jms:accounting", "jms:hr");

        // recipient list

        from("jms:processing")
                //.bean(Recipients.class)
                //.setHeader("recipients", () -> "jms:accounting")
                .setHeader("recipients", method(Recipients.class, "accounting"))
                .recipientList(header("recipients"));

        from("jms:accounting").to("log:pl.training.camel");
        from("jms:hr").to("log:pl.training.camel");
        */

        // split / aggregate

        /*from("file:module-two/source?noop=true")
                .filter(header("CamelFileName").endsWith("csv"))
                .split(bodyAs(String.class).tokenize("\n"))
                .unmarshal().bindy(BindyType.Csv, Order.class)
                .bean(OrderProcessor.class)
                .aggregate(header(Exchange.CORRELATION_ID), new OrderAggregationStrategy())
                .completionSize(2)
                .to("log:pl.training.camel");*/

        // Slip router

        from("jms:queueA").to("log:pl.training.camel");
        from("jms:queueB").to("log:pl.training.camel");
        from("jms:queueC").to("log:pl.training.camel");

        /*from("file:module-two/source?noop=true")
                .setHeader("destination").method(ComputeSlip.class)
                .routingSlip(header("destination"), ";");
        */

        // Load balancer

        from("file:module-two/source?noop=true")
              .loadBalance().roundRobin()
                .to("jms:queueA", "jms:queueB", "jms:queueC")
                .end();
    }

}
