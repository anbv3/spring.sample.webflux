package spring.sample.webflux.configs;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.concurrent.TimeUnit;

@PropertySources({@PropertySource("classpath:application.properties")})
@Configuration
public class ServerConfiguration implements WebFluxConfigurer {
    @Bean
    public OkHttpClient createOkHttpClient() {
        ConnectionPool connectionPool = new ConnectionPool(30, 15, TimeUnit.SECONDS);
        return new OkHttpClient.Builder()
                .connectionPool(connectionPool)
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();
    }

}
