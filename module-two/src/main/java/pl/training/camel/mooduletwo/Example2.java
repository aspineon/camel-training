package pl.training.camel.mooduletwo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Example2 extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("quartz://get?cron=0/15+*+*+*+*+?")
                .to("http://raw.githubusercontent.com/landrzejewski/camel-training/master/module-two/source/orders.csv")
                .convertBodyTo(String.class)
                //.process(new CustomProcessor())
                //.bean(ConverterBean.class)
                //.transform(body().regexReplaceAll(";", ","))
                .transform(new CustomExpression())
                .to("file://module-two/destination?fileName=orders-${header.Date}.csv");
                //.to("log:pl.training.camel");
    }

}
