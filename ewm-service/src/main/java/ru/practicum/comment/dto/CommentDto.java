package ru.practicum.comment.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class CommentDto {
    private Long id;
    private String text;
    private String userName;
    private String created;
    private String updated;
    private CommentDto reply;
}
