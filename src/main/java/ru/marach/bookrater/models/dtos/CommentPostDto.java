package ru.marach.bookrater.models.dtos;

import lombok.Data;
import ru.marach.bookrater.models.entities.book.Book;
import ru.marach.bookrater.models.entities.comment.Comment;
import ru.marach.bookrater.models.entities.comment.commentHistory.CommentHistoryEntry;

import javax.validation.constraints.NotNull;

@Data
public class CommentPostDto {
    @NotNull
    private String text;
    @NotNull
    private Integer rating;
}
