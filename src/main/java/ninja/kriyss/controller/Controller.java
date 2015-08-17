package ninja.kriyss.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kriyss on 17/08/2015.
 */
@RestController
public class Controller {
    @RequestMapping("/bonjour")
    public String helloWorld() {
        return "Hello World";
    }
}
