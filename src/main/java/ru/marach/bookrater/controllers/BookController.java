package ru.marach.bookrater.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.marach.bookrater.models.dtos.BookReadDto;
import ru.marach.bookrater.models.dtos.CommentPostDto;
import ru.marach.bookrater.models.dtos.CommentReadDto;
import ru.marach.bookrater.models.entities.book.Book;
import ru.marach.bookrater.models.entities.comment.Comment;
import ru.marach.bookrater.models.services.BookService;
import ru.marach.bookrater.models.services.CommentService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController()
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final CommentService commentService;

    public BookController(BookService bookService, CommentService commentService) {
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookReadDto> getAll(@RequestParam(required = false) boolean expand) {
        Stream<Book> bookStream = bookService.getAll().stream();
        return expand
                ? bookStream
                .map(Book::toExpandedReadDto)
                .collect(Collectors.toList())
                : bookStream
                .map(Book::toReadDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookReadDto getById(@PathVariable Long id, @RequestParam(required = false) boolean expand) {
        final Book book = bookService.getById(id);
        return expand
                ? book.toExpandedReadDto()
                : book.toReadDto();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookReadDto createBook(@RequestBody Book newBook, @RequestParam(required = false) boolean expand) {
        Book book = bookService.addNew(newBook);
        return expand
                ? book.toExpandedReadDto()
                : book.toReadDto();
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookReadDto updateBook(@PathVariable Long id, @RequestBody Book newBookData) {
        return bookService.updateById(id, newBookData).toReadDto();
    }

    @GetMapping("/{bookId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentReadDto> getBookComments(@PathVariable Long bookId) {
        return commentService.getBookComments(bookId).stream()
                .map(Comment::toReadDto).collect(Collectors.toList());
    }

    @PostMapping("/{bookId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentReadDto addComment(@PathVariable Long bookId, @RequestBody CommentPostDto commentPostDto) {
        final Book book = bookService.getById(bookId);
        return commentService.addComment(book, commentPostDto).toReadDto();
    }

    @PostMapping("/{bookId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentReadDto editComment(@PathVariable Long commentId,
                                      @PathVariable String bookId,
                                      @RequestBody CommentPostDto commentPostDto) {
        final Comment comment = commentService.getById(commentId);
        return commentService.editComment(comment, commentPostDto).toReadDto();
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookReadDto deleteBook(@PathVariable Long id) {
        return bookService.deleteById(id).toReadDto();
    }

    @DeleteMapping("/{bookId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentReadDto deleteComment(@PathVariable Long bookId, @PathVariable Long commentId) {
        return commentService.deleteById(commentId).toReadDto();
    }

}
