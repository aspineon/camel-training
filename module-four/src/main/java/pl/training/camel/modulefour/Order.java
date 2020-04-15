package pl.training.camel.modulefour;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "orders")
@Entity
@Data
public class Order {

    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private long price;

}
