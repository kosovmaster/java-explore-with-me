package ru.practicum.comment.service;

import ru.practicum.comment.dto.CommentDto;
import ru.practicum.comment.dto.CommentFullDto;
import ru.practicum.comment.dto.NewCommentDto;
import ru.practicum.comment.dto.UpdatedCommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long eventId, Long userId, NewCommentDto newCommentDto);

    void deleteComment(Long commentId, Long userId);

    void deleteCommentByAdmin(Long commentId);

    CommentDto updateComment(Long commentId, Long userId, UpdatedCommentDto updatedComment);

    CommentDto getCommentUser(Long commentId, Long userId);

    CommentFullDto getCommentForAdmin(Long commentId);

    List<CommentDto> getAllCommentsUser(Long userId, Integer from, Integer size);

    List<CommentDto> getAllCommentsByEvent(Long eventId, Integer from, Integer size);

    List<CommentFullDto> getAllCommentsByTextForAdmin(String text, Integer from, Integer size);

    List<CommentFullDto> getAllCommentsUserForAdmin(Long userId, Integer from, Integer size);
}
