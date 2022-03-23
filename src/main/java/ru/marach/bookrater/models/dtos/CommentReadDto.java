package ru.marach.bookrater.models.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Accessors(chain = true)
public class CommentReadDto {
    @NotNull
    private Long id;
    @NotNull
    private List<CommentHistoryEntryReadDto> history;
}
