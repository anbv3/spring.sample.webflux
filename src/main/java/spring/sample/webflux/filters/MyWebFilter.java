package spring.sample.webflux.filters;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class MyWebFilter implements WebFilter {
    private static final Logger LOG = LoggerFactory.getLogger(MyWebFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (exchange.getRequest().getRemoteAddress() != null) {
            String clientIp = exchange.getRequest().getRemoteAddress().getAddress().toString();
            exchange.getAttributes().put("clientIp", clientIp);
            LOG.info("clientIp: {}", clientIp);
        }

        return chain.filter(exchange)
                    // [NOTE] https://spring.io/blog/2017/09/28/reactor-bismuth-is-out
                    // Add some data to SubscriberContext to store the data as Thread local
                    .subscriberContext(context -> context.put("TEST", "TEST DATA"));

    }
}
