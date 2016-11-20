package me.whiteship.demo.event;

import me.whiteship.demo.domain.Event;
import me.whiteship.demo.domain.EventStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author keesun
 */
@Service
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

    @Override
    public List<Event> findRecentEvents(int size) {
        List<Event> events = new ArrayList<>();
        repository.findAll(new PageRequest(0, size, new Sort(Sort.Direction.DESC, "startDateTime"))).forEach(events::add);
        return events;
    }

    private void validate(Event event) throws EventCannotCreateException {
        if (!StringUtils.hasText(event.getName())) {
            throw new EventCannotCreateException();
        }
    }
}
