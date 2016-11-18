package me.whiteship.demo.event;

import me.whiteship.demo.domain.Event;
import me.whiteship.demo.domain.EventStatus;
import me.whiteship.demo.domain.SimpleEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author keesun
 */

public class EventServiceTest {

    SimpleEventService eventService = new SimpleEventService();

    @Mock
    EventRepository eventRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        eventService.repository = eventRepository;
    }

    @Test
    public void createSimpleEvent() {
        SimpleEvent validEvent = new SimpleEvent();
        validEvent.setName("일회성 이벤트");
        validEvent.setStartDateTime(LocalDateTime.of(2016, 11, 14, 10, 0));
        validEvent.setStartDateTime(LocalDateTime.of(2016, 11, 14, 20, 0));
        validEvent.setDescription("테스트 이벤트");
        validEvent.setHashtag("first");

        // Repository will save the event and set Id for it.
        when(eventRepository.save(validEvent)).then(new Answer<Event>() {
            @Override
            public Event answer(InvocationOnMock invocation) throws Throwable {
                validEvent.setId(1);
                return validEvent;
            }
        });

        Event createdEvent = eventService.create(validEvent);

        assertNotNull(createdEvent);
        assertNotNull(createdEvent.getId());
        assertEquals(EventStatus.PENDING, createdEvent.getEventStatus());
    }

    @Test(expected = EventCannotCreateException.class)
    public void createSimpleEvent_withoutRequiredField() {
        SimpleEvent withoutRequiredFields = new SimpleEvent();
        eventService.create(withoutRequiredFields);
    }

    @Test
    public void getSimpleEvent() {
        SimpleEvent validEvent = new SimpleEvent();
        validEvent.setName("Candle light");
        validEvent.setHashtag("haya");

        // Repository will save the event and set Id for it.
        when(eventRepository.save(validEvent)).then(new Answer<Event>() {
            @Override
            public Event answer(InvocationOnMock invocation) throws Throwable {
                validEvent.setId(1);
                return validEvent;
            }
        });

        Event createdEvent = eventService.create(validEvent);
        assertNotNull(createdEvent.getId());
        assertNotNull(createdEvent.getHashtag());

        // Repository will return event for the hashTag.
        when(eventRepository.findByHashtag("haya")).thenReturn(validEvent);

        Event foundEvent = eventService.findOneByHashtag("haya");
        assertEquals("Candle light", foundEvent.getName());
    }

}