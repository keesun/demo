package me.whiteship.demo.event;

import me.whiteship.demo.Application;
import me.whiteship.demo.domain.Event;
import me.whiteship.demo.domain.SimpleEvent;
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

        test1 = createSimpleEvent("test", LocalDateTime.of(2016, 11, 18, 21 ,45));
        eventRepository.save(test1);
        assertEquals(1, eventRepository.count());
        test2 = createSimpleEvent("test2", LocalDateTime.of(2016, 11, 19, 10, 0));
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
        Iterable<Event> iterable = eventRepository.findAll(new Sort(Sort.Direction.DESC, "startDateTime"));
        iterable.forEach(events::add);
        events.forEach(System.out::println);
        assertEquals(test1.getId(), events.get(0).getId());
        assertEquals(test2.getId(), events.get(1).getId());
    }

    private SimpleEvent createSimpleEvent(String name, LocalDateTime startDateTime) {
        SimpleEvent event = new SimpleEvent();
        event.setName(name);
        event.setStartDateTime(startDateTime);
        event.setEndDateTime(LocalDateTime.of(2016, 11, 17, 21, 40));
        event.setDescription("testing redis repository");
        event.setHashtag(name);
        return event;
    }

}
