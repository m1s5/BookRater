package ru.marach.bookrater.models.entities.book;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.marach.bookrater.models.dtos.BookReadDto;
import ru.marach.bookrater.models.entities.comment.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Data
@Accessors(chain = true)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String isbnCode;
    @NotNull
    private String title;
    private String description;
    @NotNull
    private String author;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<Comment> comments;

    public BookReadDto toReadDto() {
        return new BookReadDto()
                .setId(id)
                .setIsbnCode(isbnCode)
                .setTitle(title)
                .setDescription(description)
                .setAuthor(author);
    }
}