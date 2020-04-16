package pl.training.camel.modulefive;

import org.apache.camel.Endpoint;
import org.apache.camel.support.DefaultComponent;

import java.util.Map;

public class As400Component extends DefaultComponent {

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        As400Endpoint as400Endpoint = new As400Endpoint(uri, this);
        as400Endpoint.setName(remaining);
        return as400Endpoint;
    }

}
