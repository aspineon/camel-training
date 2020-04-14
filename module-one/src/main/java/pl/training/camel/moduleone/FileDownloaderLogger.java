package pl.training.camel.moduleone;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FileDownloaderLogger implements Processor {

    @Override
    public void process(Exchange exchange) {
        String fileName = exchange.getIn().getHeader("CamelFileName").toString();
        System.out.println("New file downloaded: " + fileName);
    }

}
