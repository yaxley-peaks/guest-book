package in.yaxley.gtodo;

import in.yaxley.gtodo.Entities.TodoEntry;
import in.yaxley.gtodo.Repositories.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNullApi;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(TodoRepository repository) {
        return args -> {
            repository.save(new TodoEntry(1, "TODO 1", false));
            repository.save(new TodoEntry(1, "TODO 2", true));
            repository.save(new TodoEntry(1, "TODO 3", false));
            repository.save(new TodoEntry(1, "TODO 4", true));
        };
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // allow all origins on all endpoints
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}
