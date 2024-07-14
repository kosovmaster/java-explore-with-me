package ru.practicum.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comment.dto.CommentDto;
import ru.practicum.comment.service.CommentService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Validated
public class PublicCommentController {
    private final CommentService commentService;

    @GetMapping("/event/{eventId}")
    public List<CommentDto> getAllCommentsByEvent(@PathVariable @NotNull @Positive Long eventId,
                                                  @RequestParam(defaultValue = "0", required = false) Integer from,
                                                  @RequestParam(defaultValue = "10", required = false) Integer size) {
        return commentService.getAllCommentsByEvent(eventId, from, size);
    }
}
