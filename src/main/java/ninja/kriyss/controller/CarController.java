package ninja.kriyss.controller;

import ninja.kriyss.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kriyss on 17/08/2015.
 */
@RestController
@RequestMapping("/cars")
public class CarController {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List<Car> getCars() {
        return Arrays.asList(
                new Car("nom1", "rouge"),
                new Car("nom2", "bleu"),
                new Car("nom3", "vert"),
                new Car("nom4", "jaune")
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/1", method = RequestMethod.GET)
    public Car getCar() {
        return new Car("nom4", "jaune");
    }
}
