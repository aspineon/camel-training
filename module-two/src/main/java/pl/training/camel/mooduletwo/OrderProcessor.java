package pl.training.camel.mooduletwo;

import org.springframework.stereotype.Component;

@Component
public class OrderProcessor {

    public Order process(Order order) {
        order.setName(order.getName().toUpperCase());
        return order;
    }

}
