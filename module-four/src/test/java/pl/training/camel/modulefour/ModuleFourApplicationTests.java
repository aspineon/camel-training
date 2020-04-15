package pl.training.camel.modulefour;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.apache.camel.component.mock.MockEndpoint;

import java.io.File;

import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = ModuleFourApplication.class)
@RunWith(CamelSpringBootRunner.class)
public class ModuleFourApplicationTests {

    @Autowired
    private ProducerTemplate template;
    @Autowired
    private CamelContext camelContext;

    @Test
    public void shouldMoveFile() {
        NotifyBuilder notifyBuilder = new NotifyBuilder(camelContext)
                .whenDone(1).create();
        template.sendBodyAndHeader("file://module-four/target/source", "Hello", Exchange.FILE_NAME, "hello.txt");
        assertTrue(notifyBuilder.matchesWaitTime());
        File target = new File("module-four/target/destination/hello.txt");
        assertTrue(target.exists());
    }

    @Test
    public void shouldReceiveMessage() throws InterruptedException {
        MockEndpoint mockEndpoint = camelContext.getEndpoint("mock:training", MockEndpoint.class);
        mockEndpoint.expectedMessageCount(1);
        mockEndpoint.expectedBodiesReceived("Camel");
        template.sendBody("stub:jms:topic:training", "Camel");
        mockEndpoint.assertIsSatisfied();
    }

}
