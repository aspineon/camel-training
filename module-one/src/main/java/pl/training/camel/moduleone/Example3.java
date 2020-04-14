package pl.training.camel.moduleone;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;

public class Example3 {

    public static void main(String[] args) throws Exception {
        FileDownloaderLogger fileDownloaderLogger = new FileDownloaderLogger();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");

        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("ftp://ftp.cluster029.hosting.ovh.net/www?delay=5000&delete=false&username=mobiledebw&password=RAW(xS2rDgt6e4eF)")
                        .process(fileDownloaderLogger)
                        .to("jms:placedOrder");
                from("jms:placedOrder")
                        .to("log:pl.training.camel");
            }
        });
        camelContext.start();
        Thread.sleep(10_000);
        camelContext.stop();
    }

}
