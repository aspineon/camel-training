package pl.training.camel.mooduletwo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.model.dataformat.JacksonXMLDataFormat;
import org.springframework.stereotype.Component;

@Component
public class Example2 extends RouteBuilder {

    @Override
    public void configure() {
        JacksonXMLDataFormat xmlDataFormat = new JacksonXMLDataFormat();
        xmlDataFormat.setPrettyPrint("true");

        from("quartz://get?cron=0/15+*+*+*+*+?")
                .to("http://raw.githubusercontent.com/landrzejewski/camel-training/master/module-two/source/orders.csv")
                .convertBodyTo(String.class)
                //.process(new CustomProcessor())
                //.bean(ConverterBean.class)
                //.transform(body().regexReplaceAll(";", ","))
                .transform(new CustomExpression())
                .unmarshal().bindy(BindyType.Csv, Order.class)
                //.marshal().json()
                .marshal(xmlDataFormat)
                //.to("file://module-two/destination?fileName=orders-${header.Date}.csv");
                .to("log:pl.training.camel");
    }

}
