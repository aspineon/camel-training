package pl.training.camel.mooduletwo;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.List;

public class OrderAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            List<Order> orders = new ArrayList<>();
            Order newOrder = newExchange.getIn().getBody(Order.class);
            orders.add(newOrder);
            newExchange.getIn().setBody(orders);
            return newExchange;
        }
        List<Order> orders = oldExchange.getIn().getBody(List.class);
        Order newOrder = newExchange.getIn().getBody(Order.class);
        orders.add(newOrder);
        return oldExchange;
    }

}
