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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
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

    @Test
    public void findRecentEvents() {
        List<Event> events = new ArrayList<>();
        events.add(new SimpleEvent());
        events.add(new SimpleEvent());
        PageImpl<Event> page = new PageImpl<>(events);
        given(eventRepository.findAll(any(PageRequest.class))).willReturn(page);

        List<Event> recentEvents = eventService.findRecentEvents(20);

        assertEquals(2, recentEvents.size());
    }

}