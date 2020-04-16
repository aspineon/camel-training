package pl.training.camel.modulefive;

import lombok.Data;
import org.apache.camel.Component;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriPath;
import org.apache.camel.support.DefaultEndpoint;

@UriEndpoint(scheme = "as400", title = "as400", syntax = "as400:name", producerOnly = true, label = "legacy")
@Data
public class As400Endpoint extends DefaultEndpoint {

    @UriPath
    private String name;

    public As400Endpoint(String uri, Component component) {
        super(uri, component);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public Producer createProducer() throws Exception {
        return new As400Producer(this);
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        throw new UnsupportedOperationException();
    }

}
