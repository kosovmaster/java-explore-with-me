package ru.practicum.comment.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.comment.dto.CommentDto;
import ru.practicum.comment.dto.CommentFullDto;
import ru.practicum.comment.dto.NewCommentDto;
import ru.practicum.comment.dto.UpdatedCommentDto;
import ru.practicum.comment.model.Comment;
import ru.practicum.event.model.Event;
import ru.practicum.user.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.constant.Constant.PATTERN_DATE;

@Component
public class CommentMapper {
    public CommentDto toCommentDto(Comment comment) {
        if (comment == null) {
            return null;
        }
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .userName(comment.getAuthor().getName())
                .created(comment.getCreated().format(DateTimeFormatter.ofPattern(PATTERN_DATE)))
                .updated(comment.getUpdated() != null ? comment.getUpdated().format(DateTimeFormatter.ofPattern(PATTERN_DATE)) : null)
                .reply(comment.getReply() != null ? toCommentDto(comment.getReply()) : null)
                .build();
    }

    public CommentFullDto toCommentFullDto(Comment comment) {
        return CommentFullDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .author(comment.getAuthor())
                .event(comment.getEvent())
                .created(comment.getCreated().format(DateTimeFormatter.ofPattern(PATTERN_DATE)))
                .updated(comment.getUpdated() != null ? comment.getUpdated().format(DateTimeFormatter.ofPattern(PATTERN_DATE)) : null)
                .parentComment(comment.getParentComment())
                .build();
    }

    public Comment toCommentCreate(NewCommentDto newCommentDto, Event event, User user, Comment comment) {
        return Comment.builder()
                .text(newCommentDto.getText())
                .author(user)
                .event(event)
                .created(LocalDateTime.now())
                .parentComment(comment)
                .build();
    }

    public Comment toCommentUpdate(UpdatedCommentDto updatedCommentDto, Comment comment) {
        return Comment.builder()
                .id(comment.getId())
                .text(updatedCommentDto.getText())
                .author(comment.getAuthor())
                .event(comment.getEvent())
                .created(comment.getCreated())
                .updated(LocalDateTime.now())
                .parentComment(comment.getParentComment())
                .build();
    }

    public List<CommentDto> toCommentDtoList(List<Comment> comments) {
        return comments.stream()
                .map(this::toCommentDto)
                .collect(Collectors.toList());
    }

    public List<CommentFullDto> toCommentFullDtoList(List<Comment> comments) {
        return comments.stream()
                .map(this::toCommentFullDto)
                .collect(Collectors.toList());
    }
}
