package spring.sample.webflux.handlers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.reactive.function.server.RouterFunction;
import spring.sample.webflux.WebfluxTemplateApplication;
import spring.sample.webflux.configs.ServerConfiguration;
import spring.sample.webflux.routes.MyRoute;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyHandlerTest {

    private WebTestClient client;

    @Autowired
    private ApplicationContext context;

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