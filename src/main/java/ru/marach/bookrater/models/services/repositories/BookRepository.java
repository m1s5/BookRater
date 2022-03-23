package ru.marach.bookrater.models.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.marach.bookrater.models.entities.book.Book;

import javax.validation.constraints.NotNull;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
