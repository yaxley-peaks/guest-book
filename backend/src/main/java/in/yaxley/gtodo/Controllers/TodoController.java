package in.yaxley.gtodo.Controllers;

import in.yaxley.gtodo.Entities.TodoEntry;
import in.yaxley.gtodo.Repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/todos")
public class TodoController {

    TodoRepository todos;

    @Autowired
    public TodoController(TodoRepository repo) {
       this.todos = repo;
    }

    @GetMapping("/todos/")
    public List<TodoEntry> getTodos() {
        return (List<TodoEntry>) todos.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/todos/")
    public TodoEntry addTodo(@RequestBody TodoEntry dto) {
        TodoEntry entry = new TodoEntry(dto.getUserId(), dto.getTitle(), dto.isCompleted()) ;
        todos.save(entry);
        return entry;
    }
}
