package me.whiteship.demo.configuration;

import me.whiteship.demo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PropertiesServiceTest {

    @Autowired PropertiesService propertiesService;

    @Autowired
    Environment environment;

    @Test
    public void di() {
        String jsBundleUrl = propertiesService.getJsBundleUrl();
        assertEquals(environment.getProperty("url.jsBundle"), jsBundleUrl);
    }

}