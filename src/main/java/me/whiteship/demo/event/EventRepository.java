package me.whiteship.demo.event;

import me.whiteship.demo.domain.Event;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author keesun
 */
@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {

    Event findByHashtag(String hashtag);

}
