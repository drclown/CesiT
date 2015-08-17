package ninja.kriyss.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @RequestMapping("/bonjour")
    public String helloWorld() {
        return "Hello World";
    }

    @RequestMapping("/bonjour/{prenom}")
    public String helloWorld(
            @PathVariable String prenom,
            @RequestParam String valeur) {
        return "Hello " + prenom + " valeur : " + valeur;
    }
}
