package me.whiteship.demo.event;

import me.whiteship.demo.Application;
import me.whiteship.demo.domain.Event;
import me.whiteship.demo.domain.SimpleEvent;
import me.whiteship.demo.tesiri.Tesiri;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * To run this test you should run redis-server on port 7777.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EventRepositoryTest {

    @Autowired EventRepository eventRepository;

    SimpleEvent test1;

    SimpleEvent test2;

    @Before
    public void setup() {
        eventRepository.deleteAll();
        assertEquals(0, eventRepository.count());

        Tesiri tesiri = new Tesiri();
        test1 = tesiri.create(SimpleEvent.class);
        test1.setHashtag("test");
        eventRepository.save(test1);
        assertEquals(1, eventRepository.count());
        test2 = tesiri.create(SimpleEvent.class);
        test2.setHashtag("test2");
        eventRepository.save(test2);
        assertEquals(2, eventRepository.count());
    }

    @Test
    public void findByHashtag() {
        Event foundEvent = eventRepository.findByHashtag("test");
        assertEquals(test1.getId(), foundEvent.getId());

        foundEvent = eventRepository.findByHashtag("test2");
        assertEquals(test2.getId(), foundEvent.getId());
    }

    @Test
    public void findAll() {
        List<Event> events = new ArrayList<>();
        Iterable<Event> iterable = eventRepository.findAll();
        iterable.forEach(events::add);
        assertEquals(2, events.size());
    }

}
