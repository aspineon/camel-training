package pl.training.camel.modulethree;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("greetings")
    public String sayHello() {
        return "Hello!";
    }

}
