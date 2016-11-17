package me.whiteship.demo.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author keesun
 */
@Data
public abstract class Event {

    private Integer id;

    private String name;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String description;

    private EventStatus eventStatus;

    /**
     * Temporal token for this event.
     * This value can be used to invite people and should be human-readable and not too long.
     * This value will be removed after this event ended.
     */
    private String hashtag;

}
