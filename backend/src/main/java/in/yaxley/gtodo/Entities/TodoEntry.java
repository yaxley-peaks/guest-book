package in.yaxley.gtodo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public final class TodoEntry {
    public TodoEntry(int userId,  String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int userId;
    String title;
    boolean completed;

    public TodoEntry() { }
}