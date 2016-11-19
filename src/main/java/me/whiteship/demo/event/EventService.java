package me.whiteship.demo.event;

import me.whiteship.demo.domain.Event;

import java.util.List;

/**
 * @author keesun
 */
public interface EventService {
    Event create(Event event);

    Event findOneByHashtag(String hashtag);

    List<Event> findRecentEvents(int size);
}
