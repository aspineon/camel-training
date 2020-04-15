package pl.training.camel.modulethree;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public Order addOrder(Order order) {
        return ordersRepository.saveAndFlush(order);
    }

}
