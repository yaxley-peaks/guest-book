package in.yaxley.gtodo.Controllers;

import in.yaxley.gtodo.Entities.TodoEntry;
import in.yaxley.gtodo.Repositories.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/todos/{id}")
    public TodoEntry changeTodo(@PathVariable int id, @RequestBody TodoEntry dto) {
        Optional<TodoEntry> existingEntry = todos.findById(id);
        if(existingEntry.isEmpty()) {
            throw new EntityNotFoundException();
        }
        TodoEntry eEntry = existingEntry.get();
        eEntry.setTitle(dto.getTitle());
        eEntry.setCompleted(dto.isCompleted());
        todos.save(eEntry);
        return eEntry;
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable int id) {
        Optional<TodoEntry> oEntry = todos.findById(id);
        if(oEntry.isEmpty()) {
            throw new EntityNotFoundException();
        }
        todos.delete(oEntry.get());
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ignored) {
        return ResponseEntity.notFound().build();
    }
}
