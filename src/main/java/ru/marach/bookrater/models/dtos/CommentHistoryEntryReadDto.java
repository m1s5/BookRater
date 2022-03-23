package ru.marach.bookrater.models.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CommentHistoryEntryReadDto {
    @NotNull
    private Long id;
    @NotNull
    private String text;
    @NotNull
    private Integer rating;
    @NotNull
    private LocalDateTime dateTime = LocalDateTime.now();

}
