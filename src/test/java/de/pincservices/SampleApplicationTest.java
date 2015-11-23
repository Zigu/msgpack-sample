package de.pincservices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = SampleApplication.class)
@IntegrationTest("server.port:0")
public class SampleApplicationTest {

    @Value("${local.server.port}")
    private int port;


    @Before
    public void init() {
        RestAssured.port = port;
    }

    @Test
    public void test() throws Exception {
        given().when().body(sampleNotification()).contentType("application/x-msgpack").post("/notifications").then().statusCode(200);
    }

    private byte[] sampleNotification() throws Exception {
        final Notification notification = new Notification();
        notification.setId(1);
        notification.setText("Check 12");

        ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());

        final byte[] bytes = objectMapper.writeValueAsBytes(notification);


        System.out.println("Converted " + notification + " to msgpack. (" + new String(bytes) + ")");

        return bytes;
    }

}