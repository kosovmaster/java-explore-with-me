package ru.practicum.comment.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.comment.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE LOWER(c.text) LIKE CONCAT('%',LOWER(:text),'%')")
    Optional<List<Comment>> findAllByText(String text, Pageable pageable);

    Optional<List<Comment>> findAllByEventIdAndParentComment(Long eventId, Comment parentComment, Pageable pageable);

    Optional<List<Comment>> findAllByAuthorId(Long userId, Pageable pageable);

    Optional<List<Comment>> findAllByParentCommentIdIn(List<Long> commentId);

    Optional<Comment> findByIdAndAuthorId(Long commentId, Long userId);

    Optional<Comment> findByIdAndEventId(Long commentId, Long eventId);
}
