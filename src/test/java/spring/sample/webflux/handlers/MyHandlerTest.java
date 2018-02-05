package spring.sample.webflux.handlers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyHandlerTest {

    @Autowired
    private WebApplicationContext context;

    private WebTestClient client;

    @Before
    public void setUp() {
        this.client = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    public void helloWorld() {
        this.client.get()
                   .uri("/")
                   .exchange()
                   .expectStatus()
                   .isOk();
    }

    @Test
    public void handleEcho() {
        this.client.get()
                   .uri("/echo/test")
                   .header("TEST", "TEST")
                   .exchange()
                   .expectStatus().isOk()
                   .expectBody(String.class).isEqualTo("test");
    }
}