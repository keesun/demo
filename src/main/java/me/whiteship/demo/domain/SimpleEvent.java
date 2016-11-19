package me.whiteship.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author keesun
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SimpleEvent extends Event {
}
