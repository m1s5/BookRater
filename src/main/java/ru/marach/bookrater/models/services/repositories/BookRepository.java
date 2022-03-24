package ru.marach.bookrater.models.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.marach.bookrater.models.entities.book.Book;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByTitleLikeIgnoreCaseOrDescriptionLikeIgnoreCaseOrIsbnCodeLikeIgnoreCaseOrAuthorLikeIgnoreCase(@NotNull String titleSearch,
                                                                                       @NotNull String description,
                                                                                       @NotNull String isbnSearch,
                                                                                       @NotNull String authorSearch);

//    List<Book> findAllByDescriptionLike(@NotNull String description);
//
//    List<Book> findAllByAuthorLike(@NotNull String author);
//
//    List<Book> findByIsbnCodeLike(@NotNull String isbnCode);
}
