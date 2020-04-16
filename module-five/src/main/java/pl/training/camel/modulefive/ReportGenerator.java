package pl.training.camel.modulefive;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Component
@Log
public class ReportGenerator {

    public Entry process(String row) {
        String[] fields = row.split(",");
        return new Entry(fields[3], fields[0]);
    }

    public void save(Entry entry) throws InterruptedException {
        Thread.sleep(100);
        log.info("Entry saved: " + entry);
    }

}
