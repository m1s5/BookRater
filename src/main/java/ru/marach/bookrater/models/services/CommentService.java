package ru.marach.bookrater.models.services;

import org.springframework.stereotype.Service;
import ru.marach.bookrater.models.dtos.CommentPostDto;
import ru.marach.bookrater.models.entities.book.Book;
import ru.marach.bookrater.models.entities.book.BookNotFoundException;
import ru.marach.bookrater.models.entities.comment.Comment;
import ru.marach.bookrater.models.entities.comment.CommentNotFoundException;
import ru.marach.bookrater.models.services.repositories.CommentRepository;

import java.util.List;

@Service
public class CommentService {
    private final BookService bookService;
    private final CommentRepository commentRepository;

    public CommentService(BookService bookService, CommentRepository commentRepository) {
        this.bookService = bookService;
        this.commentRepository = commentRepository;
    }


    public List<Comment> getBookComments(Long bookId) {
        Book book = bookService.getById(bookId);
        return book.getComments();
    }

    public Comment getById(Long id) {
        return commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
    }

    public Comment addComment(Book book, CommentPostDto commentPostDto) {
        Comment comment = new Comment();
        comment.setBook(book);
        comment.addHistoryEntry(commentPostDto);
        book.getComments().add(comment);
        return commentRepository.save(comment);
    }

    public Comment editComment(Comment comment, CommentPostDto commentPostDto) {
        comment.addHistoryEntry(commentPostDto);
        return commentRepository.save(comment);
    }


    public Comment deleteById(Long id) {
        if (commentRepository.existsById(id)) {
            Comment comment = commentRepository.getById(id);
            commentRepository.deleteById(id);
            return comment;
        } else throw new BookNotFoundException();

    }
}
