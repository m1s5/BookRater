package ru.marach.bookrater.models.entities.comment.commentHistory;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import ru.marach.bookrater.models.dtos.CommentHistoryEntryReadDto;
import ru.marach.bookrater.models.entities.comment.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "COMMENT_HISTORY")
public class CommentHistoryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "COMMENT_ID")
    private Comment comment;
    @NotNull
    private String text;
    @NotNull
    private Integer rating;
    @NotNull
    @CreationTimestamp
    private LocalDateTime dateTime = LocalDateTime.now();

    public CommentHistoryEntryReadDto toReadDto() {
        return new CommentHistoryEntryReadDto()
                .setId(id)
                .setText(text)
                .setRating(rating)
                .setDateTime(dateTime);
    }
}
