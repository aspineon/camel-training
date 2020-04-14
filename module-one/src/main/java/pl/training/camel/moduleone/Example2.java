package pl.training.camel.moduleone;

import org.apache.camel.main.Main;

public class Example2 {

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.addRouteBuilder(Routes.class);
        main.run();
    }

}
