package me.whiteship.demo.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.whiteship.demo.domain.SimpleEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

    @Test
    public void testCreate() throws Exception {
        SimpleEvent event = new SimpleEvent();
        event.setId(1);
        given(eventService.create(any())).willReturn(event);
        this.mvc.perform(post("/events")
                .content(objectMapper.writeValueAsString(event))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void testGet() throws Exception {
        SimpleEvent event = new SimpleEvent();
        event.setId(1);
        given(eventRepository.findOne(any())).willReturn(event);
        this.mvc.perform(get("/event/1"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(status().isOk())
                .andDo(print());
    }


}