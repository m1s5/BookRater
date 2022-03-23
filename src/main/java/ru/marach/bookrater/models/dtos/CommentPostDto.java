package ru.marach.bookrater.models.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class CommentPostDto {
    @NotNull
    private String text;
    @NotNull
    private Integer rating;
}
