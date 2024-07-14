package ru.practicum.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comment.dto.CommentFullDto;
import ru.practicum.comment.service.CommentService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/admin/comment")
@RequiredArgsConstructor
@Validated
public class AdminCommentController {
    private final CommentService commentService;

    @GetMapping("/{commentId}")
    public CommentFullDto getCommentForAdmin(@PathVariable @NotNull @Positive Long commentId) {
        return commentService.getCommentForAdmin(commentId);
    }

    @GetMapping("/user/{userId}")
    public List<CommentFullDto> getAllCommentsUserForAdmin(@PathVariable @NotNull @Positive Long userId,
                                                           @RequestParam(defaultValue = "0", required = false) Integer from,
                                                           @RequestParam(defaultValue = "10", required = false) Integer size) {
        return commentService.getAllCommentsUserForAdmin(userId, from, size);
    }

    @GetMapping
    public List<CommentFullDto> getAllCommentsByTextForAdmin(@RequestParam @NotBlank String text,
                                                             @RequestParam(defaultValue = "0", required = false) Integer from,
                                                             @RequestParam(defaultValue = "10", required = false) Integer size) {
        return commentService.getAllCommentsByTextForAdmin(text, from, size);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentByAdmin(@PathVariable @NotNull @Positive Long commentId) {
        commentService.deleteCommentByAdmin(commentId);
    }
}
