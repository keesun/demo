package me.whiteship.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author keesun
 */
@Data
@RedisHash("event")
public abstract class Event {

    @Id
    private Integer id;

    @Indexed
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDateTime;

    private String description;

    private EventStatus eventStatus;

    /**
     * Temporal token for this event.
     * This value can be used to invite people and should be human-readable and not too long.
     * This value will be removed after this event ended.
     */
    @Indexed
    private String hashtag;

}
