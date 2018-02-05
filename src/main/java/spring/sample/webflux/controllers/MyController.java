package spring.sample.webflux.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class MyController {

    // [NOTE] https://github.com/simonbasle-demos/reactor-by-example

    @GetMapping("controller/echo/{echo}")
    public Mono<String> echo(@PathVariable String echo) {
        return Mono.just(echo)
                   .map(e -> e);
    }

    @GetMapping("controller/delay/{echo}")
    public Mono<String> helloDelay(@PathVariable String echo) {
        return Mono.just(echo)
                   .delaySubscription(Duration.ofSeconds(3));
    }

}
