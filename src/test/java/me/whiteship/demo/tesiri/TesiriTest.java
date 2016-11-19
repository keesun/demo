package me.whiteship.demo.tesiri;

import lombok.Data;
import lombok.ToString;
import me.whiteship.demo.domain.SimpleEvent;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;


public class TesiriTest {

    @Data
    class President {

        String getOut;

        int numberOfPropofol;

        Integer crime;

        LocalDateTime localDateTime;
    }

    @Data
    @ToString(callSuper = true)
    class JungUra extends President {

        String horse;

    }

    @Test
    public void innerClass() {
        Tesiri tesiri = new Tesiri();
        President president = tesiri.create(President.class);
        assertNotNull(president.getGetOut());
        assertNotEquals(0, president.getNumberOfPropofol());
        assertNotNull(president.getCrime());
        assertNotNull(president.getLocalDateTime());
    }

    @Test
    public void childClass() {
        Tesiri tesiri = new Tesiri();
        JungUra jungUra = tesiri.create(JungUra.class);
        assertNotNull(jungUra.getGetOut());
        assertNotEquals(0, jungUra.getNumberOfPropofol());
        assertNotNull(jungUra.getCrime());
        assertNotNull(jungUra.getLocalDateTime());
        assertNotNull(jungUra.getHorse());
    }

    @Test
    public void SimpleEvent() {
        Tesiri tesiri = new Tesiri();
        SimpleEvent simpleEvent = tesiri.create(SimpleEvent.class);
        System.out.println(simpleEvent);
    }

}