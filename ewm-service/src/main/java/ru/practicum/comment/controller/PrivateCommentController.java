package ru.practicum.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comment.dto.CommentDto;
import ru.practicum.comment.dto.NewCommentDto;
import ru.practicum.comment.dto.UpdatedCommentDto;
import ru.practicum.comment.service.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Validated
public class PrivateCommentController {
    private final CommentService commentService;

    @PostMapping("/event/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(@PathVariable @NotNull @Positive Long eventId,
                                    @PathVariable @NotNull @Positive Long userId,
                                    @Valid @RequestBody NewCommentDto newCommentDto) {
        return commentService.createComment(eventId, userId, newCommentDto);
    }

    @PatchMapping("/{commentId}")
    public CommentDto updateComment(@PathVariable @NotNull @Positive Long commentId,
                                    @PathVariable @NotNull @Positive Long userId,
                                    @Valid @RequestBody UpdatedCommentDto updatedCommentDto) {
        return commentService.updateComment(commentId, userId, updatedCommentDto);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable @NotNull @Positive Long commentId,
                              @PathVariable @NotNull @Positive Long userId) {
        commentService.deleteComment(commentId, userId);
    }

    @GetMapping("/{commentId}")
    public CommentDto getComment(@PathVariable @NotNull @Positive Long commentId,
                                 @PathVariable @NotNull @Positive Long userId) {
        return commentService.getCommentUser(commentId, userId);
    }

    @GetMapping("/user/{userId}")
    public List<CommentDto> getAllCommentsUser(@PathVariable @NotNull @Positive Long userId,
                                               @RequestParam(defaultValue = "0", required = false) Integer from,
                                               @RequestParam(defaultValue = "10", required = false) Integer size) {
        return commentService.getAllCommentsUser(userId, from, size);
    }
}
