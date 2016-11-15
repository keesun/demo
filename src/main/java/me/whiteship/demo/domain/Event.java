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

}
