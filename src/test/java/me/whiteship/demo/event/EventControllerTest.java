package me.whiteship.demo.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.whiteship.demo.domain.Event;
import me.whiteship.demo.domain.SimpleEvent;
import me.whiteship.demo.tesiri.Tesiri;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EventService eventService;

    @MockBean
    private EventRepository eventRepository;

    private Tesiri tesiri = new Tesiri();

    @Test
    public void testCreate() throws Exception {
        SimpleEvent event = tesiri.create(SimpleEvent.class);
        given(eventService.create(any())).willReturn(event);
        this.mvc.perform(post("/events")
                .content(objectMapper.writeValueAsString(event))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(event.getId())))
                .andDo(print());
    }

    @Test
    public void testGet() throws Exception {
        SimpleEvent event = tesiri.create(SimpleEvent.class);
        given(eventRepository.findOne(any())).willReturn(event);
        this.mvc.perform(get("/events/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(event.getId())))
                .andDo(print());
    }

    @Test
    public void testList() throws Exception {
        List<Event> events = new ArrayList<>();
        events.add(tesiri.create(SimpleEvent.class));
        events.add(tesiri.create(SimpleEvent.class));
        given(eventService.findRecentEvents(20)).willReturn(events);
        this.mvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(print());
    }


}