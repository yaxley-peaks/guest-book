package in.yaxley.gtodo.Entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "entries", schema = "guest_book")
public final class TodoEntry {
    public TodoEntry(int userId,  String title, boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    int id;

    @Column(name="userid")
    int userId;
    @Column(name="title")
    String title;
    @Column(name="completed")
    boolean completed;

    public TodoEntry() { }
}