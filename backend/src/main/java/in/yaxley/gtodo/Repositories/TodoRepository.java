package in.yaxley.gtodo.Repositories;

import in.yaxley.gtodo.Entities.TodoEntry;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository  extends CrudRepository<TodoEntry, Integer> {
}
