package pl.training.camel.modulefour;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

//@Component
public class Routes extends RouteBuilder {

    @Override
    public void configure() {
        /*from("quartz://get?cron=0/15+*+*+*+*+?")
                .to("http://localhost:8080/greetings")
                .convertBodyTo(String.class)
                .to("log:pl.training.camel");*/

        //from("file:module-four/source?noop=true")

        /*rest("/orders")
                .consumes("application/xml")
                .post()
                .to("direct:orders");

        from("direct:orders")
                .multicast()
                .parallelProcessing()
                .to("direct:sender", "direct:saver");

        from("direct:sender")
                .unmarshal().jacksonxml(Order.class)
                .marshal().json()
                .to("http://localhost:8080/orders?bridgeEndpoint=true")
                .unmarshal().json(JsonLibrary.Jackson, Order.class)
                .to("log:pl.training.camel");

        from("direct:saver")
                .to("bean:orderToSql")
                .to("jdbc:dataSource?useHeadersAsParameters=true");*/

    }

}
