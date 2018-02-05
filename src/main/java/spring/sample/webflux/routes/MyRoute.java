package spring.sample.webflux.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import spring.sample.webflux.handlers.MyHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MyRoute {
    private static final Logger LOG = LoggerFactory.getLogger(MyRoute.class);

    private final MyHandler myHandler;

    public MyRoute(MyHandler myHandler) {
        this.myHandler = myHandler;
    }

    @Bean
    public RouterFunction<?> routerFunction() {
        return route(GET("/echo/{path}"), myHandler::handleEcho)
                .filter(myHandlerFilter)
                .andRoute(GET("/"), myHandler::helloWorld);
    }

    HandlerFilterFunction myHandlerFilter = (request, next) -> {
        LOG.info("==== Before... ");
        Mono<? extends ServerResponse> res = next.handle(request);
        LOG.info("==== After... ");
        return res;
    };
}
