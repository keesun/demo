package me.whiteship.demo.event;

import me.whiteship.demo.domain.Event;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentMap;

/**
 * @author keesun
 */
@Repository
public class EventRepository {

    private Integer sequence = 0;

    // ID, Event
    private ConcurrentMap<Integer, Event> allEvents;

    // Hashtag, Event
    private ConcurrentMap<String, Event> liveEvents;


    public Event save(Event event) {
        Integer id = generateId();
        event.setId(id);
        allEvents.put(id, event);
        liveEvents.put(event.getHashtag(), event);
        return event;
    }

    private Integer generateId() {
        return this.sequence++;
    }

    public Event findByHashtag(String hashtag) {
        return liveEvents.get(hashtag);
    }
}
