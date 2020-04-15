package pl.training.camel.mooduletwo;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.List;

public class OrderAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }
        Order oldOrder = oldExchange.getIn().getBody(Order.class);
        Order newOrder = newExchange.getIn().getBody(Order.class);
        List<Order> orders = new ArrayList<>();
        orders.add(oldOrder);
        orders.add(newOrder);
        oldExchange.getIn().setBody(orders);
        return oldExchange;
    }

}
