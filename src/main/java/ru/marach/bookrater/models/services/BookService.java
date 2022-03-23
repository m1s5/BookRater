package ru.marach.bookrater.models.services;

import org.springframework.stereotype.Service;
import ru.marach.bookrater.models.entities.book.Book;
import ru.marach.bookrater.models.entities.book.BookNotFoundException;
import ru.marach.bookrater.models.services.repositories.BookRepository;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public Book addNew(Book book) {
        return bookRepository.save(book);
    }

    public Book updateById(Long id, Book book) {
        book.setId(id);
        if (bookRepository.existsById(id)) {
            return bookRepository.save(book);
        } else throw new BookNotFoundException();
    }

    public Book deleteById(Long id) {
        if (bookRepository.existsById(id)) {
            Book book = bookRepository.getById(id);
            bookRepository.deleteById(id);
            return book;
        } else throw new BookNotFoundException();

    }
}
