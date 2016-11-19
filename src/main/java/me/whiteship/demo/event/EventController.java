package me.whiteship.demo.event;

import me.whiteship.demo.domain.SimpleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author keesun
 */
@RestController
public class EventController {

    @Autowired EventService service;

    @Autowired EventRepository repository;

    @RequestMapping(method = RequestMethod.POST, value = "/events")
    public ResponseEntity create(@RequestBody SimpleEvent simpleEvent) {
        return new ResponseEntity<>(service.create(simpleEvent), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/event/{id}")
    public ResponseEntity get(@PathVariable Integer id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }

}
