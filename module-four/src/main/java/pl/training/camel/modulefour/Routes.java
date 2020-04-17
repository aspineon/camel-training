package pl.training.camel.modulefour;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.CryptoDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
@Data
public class Routes extends RouteBuilder {

    @Value("${files.path}")
    private String filesPath;
    @Value("${orders-service}")
    private String ordersService;

    @Override
    public void configure() {
        /*from("quartz://get?cron=0/15+*+*+*+*+?")
                .to("http://localhost:8080/greetings")
                .convertBodyTo(String.class)
                .to("log:pl.training.camel");*/


        //from("file:" + filesPath + "?noop=true")

        rest("/orders")
                .consumes("application/xml")
                .post()
                .to("direct:orders");

        CryptoDataFormat cryptoDataFormat = new CryptoDataFormat();
        cryptoDataFormat.setAlgorithm("DES");
        cryptoDataFormat.setKeyRef("secretKey");

        from("direct:orders")
                .convertBodyTo(String.class)
                .to("crypto:sign://rsa?privateKey=#securityPrivateKey")
                .marshal(cryptoDataFormat)
                .to("log:pl.training.camel")
                .unmarshal(cryptoDataFormat)
                .to("log:pl.training.camel")
                .multicast()
                .parallelProcessing()
                .to("direct:sender"/*, "direct:saver"*/);

        from("direct:sender")
                .to("crypto:verify://rsa?publicKey=#securityPublicKey")
                .unmarshal().jacksonxml(Order.class)
                .marshal().json()
                .to("http://" + ordersService + "/orders?bridgeEndpoint=true")
                .unmarshal().json(JsonLibrary.Jackson, Order.class)
                .to("log:pl.training.camel");

        /*from("direct:saver")
                .to("bean:orderToSql")
                .to("jdbc:dataSource?useHeadersAsParameters=true");*/

    }

}
