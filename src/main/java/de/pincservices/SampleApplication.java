package de.pincservices;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class SampleApplication extends WebMvcConfigurerAdapter {

    @RequestMapping(value = "/notifications", method = RequestMethod.POST, consumes = "application/x-msgpack")
    public void receive(@RequestBody Notification value) {
        System.out.println("received " + value);
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(new MsgPackHttpMessageConverter());
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleApplication.class, args);
    }
}
