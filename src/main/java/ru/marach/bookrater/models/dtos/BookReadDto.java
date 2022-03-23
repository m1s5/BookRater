package ru.marach.bookrater.models.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class BookReadDto {
    @NotNull
    @Id
    private Long id;
    @NotNull
    private String isbnCode;
    @NotNull
    private String title;
    private String description;
    @NotNull
    private String author;

}
