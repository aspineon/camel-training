package pl.training.camel.modulefive;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class Routes extends RouteBuilder {

    private ExecutorService executorService = Executors.newFixedThreadPool(20);

    @Override
    public void configure() throws Exception {
        from("file:module-five/source?noop=true")
                .log("Processing file ${header.CamelFileName}")
                .split(body().tokenize("\n"))
                    .streaming()
                    .executorService(executorService)
                    //.parallelProcessing()
                .bean("reportGenerator", "process")
                //.to("direct:update")
                  .to("seda:update")
                .end()
                .log("File ${header.CamelFileName} prcessed");

        from("seda:update?concurrentConsumers=20")
                .bean("reportGenerator", "save");

    }

}
