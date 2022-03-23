package ru.marach.bookrater.models.entities.comment;

import lombok.Data;
import ru.marach.bookrater.models.dtos.CommentPostDto;
import ru.marach.bookrater.models.dtos.CommentReadDto;
import ru.marach.bookrater.models.entities.book.Book;
import ru.marach.bookrater.models.entities.comment.commentHistory.CommentHistoryEntry;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @JoinColumn(name = "BOOK_ID")
    @ManyToOne
    private Book book;
    @NotNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comment")
    private List<CommentHistoryEntry> commentHistoryEntryList = new ArrayList<>();


    public CommentReadDto toReadDto() {
        return new CommentReadDto()
                .setId(id)
                .setHistory(commentHistoryEntryList.stream()
                        .map(CommentHistoryEntry::toReadDto)
                        .collect(Collectors.toList()));
    }

    public CommentHistoryEntry addHistoryEntry(CommentPostDto commentPostDto) {
        CommentHistoryEntry commentHistoryEntry = new CommentHistoryEntry()
                .setComment(this)
                .setText(commentPostDto.getText())
                .setRating(commentPostDto.getRating());
        commentHistoryEntryList.add(commentHistoryEntry);
        return commentHistoryEntry;
    }

}
