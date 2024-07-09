package ru.practicum.participation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.participation.dto.ParticipationRequestDto;
import ru.practicum.participation.service.ParticipationRequestService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/requests")
@RequiredArgsConstructor
@Validated
public class PrivateUserParticipationRequestController {
    private final ParticipationRequestService participationRequestService;

    @GetMapping
    public List<ParticipationRequestDto> getInfoOnRequestsForUserInOtherEvents(
            @PathVariable @NotNull @Min(1L) Long userId) {
        return participationRequestService.getInfoOnRequestsForUserInOtherEvents(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto createRequestToParticipateInEvent(
            @PathVariable @NotNull @Min(1L) Long userId,
            @RequestParam(required = false) Long eventId) {
        return participationRequestService.createRequestToParticipateInEvent(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelRequestToParticipateInEvent(
            @PathVariable @NotNull @Min(1L) Long userId,
            @PathVariable @NotNull @Min(1L) Long requestId) {
        return participationRequestService.cancelRequestToParticipateInEvent(userId, requestId);
    }
}
