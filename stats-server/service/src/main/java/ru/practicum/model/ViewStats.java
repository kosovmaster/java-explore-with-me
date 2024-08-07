package ru.practicum.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ViewStats {
    private String app;
    private String uri;
    private Long hits;
}
