package pl.training.camel.modulefive;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = ModuleFiveApplication.class)
@RunWith(CamelSpringBootRunner.class)
class ModuleFiveApplicationTests {

    @Autowired
    private ProducerTemplate template;
    @Autowired
    private CamelContext camelContext;

    @Test
    public void asyncTest() {
        String result = template.requestBody("http://localhost:8081/reports", "some data", String.class);
        assertEquals("As400 results", result);
    }

}
