package me.whiteship.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class PropertiesService {

    @Autowired
    Environment environment;

    public String getJsBundleUrl() {
        return environment.getProperty("url.jsBundle");
    }

}
