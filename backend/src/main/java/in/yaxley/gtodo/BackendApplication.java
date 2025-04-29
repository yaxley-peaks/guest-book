package in.yaxley.gtodo;

import in.yaxley.gtodo.Entities.TodoEntry;
import in.yaxley.gtodo.Repositories.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Slf4j
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    public CommandLineRunner hikariDataSeeder(TodoRepository repository) {
        return args -> {
            repository.save(new TodoEntry(1, "TODO 1", false));
            repository.save(new TodoEntry(1, "TODO 2", true));
            repository.save(new TodoEntry(1, "TODO 3", false));
            repository.save(new TodoEntry(1, "TODO 4", true));
            repository.save(new TodoEntry(1, "TODO 5", true));
            repository.save(new TodoEntry(1, "TODO 6", true));
        };
    }

    @Bean
    public CommandLineRunner pgDataSeeder(TodoRepository repository) {
        // seed until at minimum 10 items in db
        return args -> {
            long count = repository.count();
            if (count >= 10) return;
            long loopCount = 10 - count;

            log.atInfo().log("Postgres: seeding {} entries", loopCount);

            for (int i = 0; i < loopCount; i++) {
                TodoEntry todoEntry = new TodoEntry(1, "Todo" + i, i % 2 == 0);
                repository.save(todoEntry);
            }
        };
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // allow all origins on all endpoints
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("PUT", "POST", "GET", "DELETE", "OPTIONS", "HEAD", "PATCH");
            }
        };
    }

}
