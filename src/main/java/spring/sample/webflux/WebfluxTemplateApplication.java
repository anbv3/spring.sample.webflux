package spring.sample.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class WebfluxTemplateApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WebfluxTemplateApplication.class);
		app.setWebApplicationType(WebApplicationType.REACTIVE);
        app.run(args);
	}
}
