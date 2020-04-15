package pl.training.camel.modulefour;

import org.apache.camel.Headers;
import org.apache.camel.language.xpath.XPath;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderToSql {

    public String map(@XPath("order/@name") String name,
                      @XPath("order/@price") long price,
                      @Headers Map<String, Object> headers) {
        headers.put("orderName", name);
        headers.put("orderPrice", price);
        return "insert into orders (name, price) values (:?orderName,:?orderPrice)";
    }

}
