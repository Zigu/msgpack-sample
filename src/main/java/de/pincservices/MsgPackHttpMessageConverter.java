package de.pincservices;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import java.nio.charset.StandardCharsets;

public class MsgPackHttpMessageConverter extends AbstractJackson2HttpMessageConverter {

    public MsgPackHttpMessageConverter() {
        super(new ObjectMapper(new MessagePackFactory()), new MediaType("application", "x-msgpack", StandardCharsets.UTF_8));
    }

}
