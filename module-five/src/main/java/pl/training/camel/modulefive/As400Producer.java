package pl.training.camel.modulefive;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.camel.AsyncCallback;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultAsyncProducer;

import java.util.concurrent.ExecutorService;

public class As400Producer extends DefaultAsyncProducer {

    private ExecutorService executorService;

    public As400Producer(Endpoint endpoint) {
        super(endpoint);
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        executorService = getEndpoint().getCamelContext().getExecutorServiceManager().newFixedThreadPool(this, "As400", 50);
    }

    @Override
    protected void doStop() throws Exception {
        super.doStop();
        getEndpoint().getCamelContext().getExecutorServiceManager().shutdown(executorService);
    }

    @Override
    public boolean process(Exchange exchange, AsyncCallback callback) {
        executorService.submit(new Task(exchange, callback));
        return false;
    }


}

@Log
@AllArgsConstructor
class Task implements Runnable {

    private Exchange exchange;
    private AsyncCallback asyncCallback;

    @Override
    public void run() {
        log.info("Calling As400");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("As400 finished work");
        exchange.getOut().setBody("As400 results");
        asyncCallback.done(false);
    }
}