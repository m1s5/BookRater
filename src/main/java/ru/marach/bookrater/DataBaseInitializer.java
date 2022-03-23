package ru.marach.bookrater;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.marach.bookrater.models.dtos.CommentPostDto;
import ru.marach.bookrater.models.entities.book.Book;
import ru.marach.bookrater.models.entities.comment.Comment;
import ru.marach.bookrater.models.services.BookService;
import ru.marach.bookrater.models.services.CommentService;

import java.util.Arrays;
import java.util.List;

@Component
public class DataBaseInitializer implements ApplicationRunner {
    private final BookService bookService;
    private final CommentService commentService;

    public DataBaseInitializer(BookService bookService, CommentService commentService) {
        this.bookService = bookService;
        this.commentService = commentService;
    }


    @Override
    public void run(ApplicationArguments args) {
        final List<Book> booksData = Arrays.asList(
                new Book()
                        .setTitle("Война и Мир")
                        .setDescription("Лежит под деревом")
                        .setAuthor("Толстой")
                        .setIsbnCode("ISBN-1111"),
                new Book()
                        .setTitle("Преступление и Наказание")
                        .setDescription("Пришел и убил")
                        .setAuthor("Достоевский")
                        .setIsbnCode("ISBN-2222"),
                new Book()
                        .setTitle("Шинель")
                        .setDescription("Порвал шинель")
                        .setAuthor("Гоголь")
                        .setIsbnCode("ISBN-3333"));
        booksData.forEach(bookService::addNew);

        commentService
                .addComment(booksData.get(0),
                        new CommentPostDto()
                                .setText("Лежит и лежит.")
                                .setRating(1));
        Comment comment2 = commentService
                .addComment(booksData.get(0),
                        new CommentPostDto()
                                .setText("Встань и иди.")
                                .setRating(2));
        Comment comment3 = commentService
                .editComment(comment2,
                        new CommentPostDto()
                                .setText("Ляг и лежи.")
                                .setRating(4));
        commentService
                .editComment(comment3,
                        new CommentPostDto()
                                .setText("Встань и стой.")
                                .setRating(10));
        commentService
                .addComment(booksData.get(2),
                        new CommentPostDto()
                                .setText("Зашей")
                                .setRating(-1));
    }
}
