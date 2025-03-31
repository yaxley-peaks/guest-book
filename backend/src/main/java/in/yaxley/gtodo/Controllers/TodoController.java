package in.yaxley.gtodo.Controllers;

import in.yaxley.gtodo.Entities.TodoEntry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("/todos")
public class TodoController {
    @GetMapping("/todos/")
    public List<TodoEntry> getTodos() {
        List<TodoEntry> todoEntries = new ArrayList<>();
        todoEntries.add(new TodoEntry(1, 1, "Todo 1", false));
        todoEntries.add(new TodoEntry(1, 2, "Todo 2", true));
        todoEntries.add(new TodoEntry(1, 3, "Todo 3", false));

        return todoEntries;
    }
}
