package me.whiteship.demo.event;

import me.whiteship.demo.domain.Event;
import me.whiteship.demo.domain.EventStatus;
import me.whiteship.demo.domain.SimpleEvent;
import org.springframework.util.StringUtils;

/**
 * @author keesun
 */
public class SimpleEventService implements EventService {

    @Override
    public Event create(Event event) {
        validate(event);
        SimpleEvent simpleEvent = new SimpleEvent();
        simpleEvent.setName(event.getName());
        simpleEvent.setDescription(event.getDescription());
        simpleEvent.setStartDateTime(event.getStartDateTime());
        simpleEvent.setEndDateTime(event.getEndDateTime());
        simpleEvent.setEventStatus(EventStatus.PENDING);
        simpleEvent.setId(1); // TODO generating this value from DB
        return simpleEvent;
    }

    private void validate(Event event) throws EventCannotCreateException {
        if (!StringUtils.hasText(event.getName())) {
            throw new EventCannotCreateException();
        }
    }
}
