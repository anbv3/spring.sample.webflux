package spring.sample.webflux.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class MyController {
    private static final Logger LOG = LoggerFactory.getLogger(MyController.class);

    // [NOTE] https://github.com/simonbasle-demos/reactor-by-example
    @GetMapping("controller/echo/{echo}")
    public Mono<String> echo(@PathVariable String echo, ServerHttpRequest request) {
        LOG.info("client URI: {}", request.getURI());
        return Mono.just(echo)
                   .map(e -> e);
    }

    @GetMapping("controller/delay/{echo}")
    public Mono<String> helloDelay(@PathVariable String echo) {
        return Mono.just(echo)
                   .delaySubscription(Duration.ofSeconds(3));
    }

    @GetMapping("controller/webclient")
    public Mono<String> testWebClient() {
        return WebClient.create("https://api.github.com/")
                        .get()
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(String.class);
    }
}
