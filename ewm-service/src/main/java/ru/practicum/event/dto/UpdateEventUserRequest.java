package ru.practicum.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.practicum.event.state.UserStateAction;
import ru.practicum.location.model.Location;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventUserRequest {
    @Length(min = 10, max = 1000)
    private String annotation;
    @Min(1L)
    private Long category;
    @Length(min = 10, max = 5000)
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private Location location;
    private Boolean paid;
    @Min(0)
    private Integer participantLimit;
    private Boolean requestModeration;
    private UserStateAction stateAction;
    @Length(min = 3, max = 120)
    private String title;
}
