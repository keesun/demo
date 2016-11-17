package me.whiteship.demo.event;

import me.whiteship.demo.domain.Event;
import me.whiteship.demo.domain.EventStatus;
import me.whiteship.demo.domain.SimpleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * @author keesun
 */
public class SimpleEventService implements EventService {

    @Autowired EventRepository repository;

    @Override
    public Event create(Event event) {
        validate(event);
        event.setEventStatus(EventStatus.PENDING);
        return repository.save(event);
    }

    @Override
    public Event findOneByHashtag(String hashtag) {
        return repository.findByHashtag(hashtag);
    }

    private void validate(Event event) throws EventCannotCreateException {
        if (!StringUtils.hasText(event.getName())) {
            throw new EventCannotCreateException();
        }
    }
}
