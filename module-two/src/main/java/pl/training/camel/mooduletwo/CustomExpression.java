package pl.training.camel.mooduletwo;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;

public class CustomExpression implements Expression {

    @Override
    public <T> T evaluate(Exchange exchange, Class<T> type) {
        String text = exchange.getIn().getBody(String.class);
        return (T) text.replaceAll(";", ",");
    }

}
