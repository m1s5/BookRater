package ru.marach.bookrater.models.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.marach.bookrater.models.entities.comment.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
