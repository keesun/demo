package me.whiteship.demo.event;

import me.whiteship.demo.domain.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author keesun
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

    Event findByHashtag(String hashtag);

}
