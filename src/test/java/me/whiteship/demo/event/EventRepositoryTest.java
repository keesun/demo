package me.whiteship.demo.event;

import me.whiteship.demo.Application;
import me.whiteship.demo.domain.Event;
import me.whiteship.demo.domain.SimpleEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * To run this test you should run redis-server on port 7777.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EventRepositoryTest {

    @Autowired EventRepository eventRepository;

    @Before
    public void setup() {
        eventRepository.deleteAll();
        assertEquals(0, eventRepository.count());
    }

    @Test
    public void save() {
        SimpleEvent simpleEvent = new SimpleEvent();
        simpleEvent.setName("test");
        simpleEvent.setStartDateTime(LocalDateTime.of(2016, 11, 17, 20, 40));
        simpleEvent.setEndDateTime(LocalDateTime.of(2016, 11, 17, 21, 40));
        simpleEvent.setDescription("testing redis repository");
        simpleEvent.setHashtag("TEST");
        SimpleEvent savedEvent = eventRepository.save(simpleEvent);
        assertNotNull(savedEvent.getId());
        assertEquals(1, eventRepository.count());

        Event foundEvent = eventRepository.findByHashtag("TEST");
        assertNotNull(foundEvent.getId());
        assertEquals(foundEvent.getId(), savedEvent.getId());
    }

}
