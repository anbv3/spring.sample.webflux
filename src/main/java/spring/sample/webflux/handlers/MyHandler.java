package spring.sample.webflux.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Service
public class MyHandler {
    private static final Logger LOG = LoggerFactory.getLogger(MyHandler.class);

    public Mono<ServerResponse> helloWorld(ServerRequest request) {
        LOG.info("{}", request.attribute("clientIp").isPresent());
        return ServerResponse.ok().body(fromObject("hello world!"));
    }

    public Mono<ServerResponse> handleEcho(ServerRequest request) {
        return Mono.subscriberContext().flatMap(context -> {
            String contextData = context.get("TEST");
            LOG.info("{}", request);
            LOG.info("{}", contextData);
            return ServerResponse.ok().body(fromObject(request.pathVariable("path")));
        });
    }
}
