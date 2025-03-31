package in.yaxley.gtodo;

import in.yaxley.gtodo.Entities.TodoEntry;
import in.yaxley.gtodo.Repositories.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
}
