package ru.marach.bookrater.models.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class BookExpandedReadDto extends BookReadDto {
    private List<CommentReadDto> commentReadDtos;

    public BookExpandedReadDto(BookReadDto bookReadDto) {
        setId(bookReadDto.getId());
        setTitle(bookReadDto.getTitle());
        setDescription(bookReadDto.getDescription());
        setAuthor(bookReadDto.getAuthor());
        setIsbnCode(bookReadDto.getIsbnCode());
    }
}
