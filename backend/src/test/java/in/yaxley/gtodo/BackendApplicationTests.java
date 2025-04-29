package in.yaxley.gtodo;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.yaxley.gtodo.Entities.TodoEntry;
import in.yaxley.gtodo.Repositories.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
class BackendApplicationTests {

    private final TodoRepository todos;
    private final MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    BackendApplicationTests(TodoRepository todos, MockMvc mockMvc) {
        this.todos = todos;
        this.mockMvc = mockMvc;
    }

    private static void seedDb(TodoRepository todos) {
        final int count = 10;
        for (int i = 0; i < count; i++) {
            TodoEntry entry = new TodoEntry(1, "Todo" + i, i % 2 == 0);
            todos.save(entry);
        }
    }

    @BeforeAll
    static void beforeAll(@Autowired TodoRepository todoRepository) {
        var c = todoRepository.count();
        if (c > 0) {
            log.atInfo().log("Found {} existing todos, skipping seeding", c);
            return;
        }
        seedDb(todoRepository);
    }

    @BeforeEach
    void beforeEach() {
        log.atInfo().log("Reinitialized database");
        todos.deleteAll();
        seedDb(todos);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void getAllTodos() throws Exception {
        mockMvc.perform(get("/todos").accept("application/json").contentType("application/json")).andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(todos.count()));
    }

    @Test
    void createNewTodo() throws Exception {

        long pCount = todos.count();
        String s = objectMapper.writeValueAsString(new TodoEntry(1, "TestTodo", false));
        mockMvc.perform(post("/todos").content(s).contentType("application/json")).andExpect(status().isCreated());
        long count = todos.count();

        if (pCount + 1 != count) {
            throw new Exception(pCount + 1 + " items expected, found " + count + " items");
        }
    }


}
