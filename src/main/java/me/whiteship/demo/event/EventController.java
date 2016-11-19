package me.whiteship.demo.event;

import me.whiteship.demo.domain.SimpleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author keesun
 */
@RestController
public class EventController {

    @Autowired EventService service;

    @RequestMapping(method = RequestMethod.POST, value = "/events")
    public ResponseEntity create(@RequestBody SimpleEvent simpleEvent) {
        return new ResponseEntity<>(service.create(simpleEvent), HttpStatus.OK);
    }

}
