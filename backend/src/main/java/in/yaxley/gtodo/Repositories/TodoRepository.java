package in.yaxley.gtodo.Repositories;

import in.yaxley.gtodo.Entities.TodoEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository  extends CrudRepository<TodoEntry, Integer> {
}
