package pl.training.camel.mooduletwo;

import java.util.Random;

public class ComputeSlip {

    public String compute(String body) {
        Random random = new Random();
        if (random.nextBoolean()) {
            return "jms:queueA;jms:queueC";
        } else {
            return "jms:queueB";
        }
    }

}
