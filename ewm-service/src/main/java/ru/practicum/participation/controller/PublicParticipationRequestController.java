package ru.practicum.participation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.participation.dto.EventRequestStatusUpdateRequest;
import ru.practicum.participation.dto.EventRequestStatusUpdateResult;
import ru.practicum.participation.dto.ParticipationRequestDto;
import ru.practicum.participation.service.ParticipationRequestService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/users/{userId}/events")
@Validated
@RequiredArgsConstructor
public class PublicParticipationRequestController {
    private final ParticipationRequestService participationRequestService;

    @PatchMapping("/{eventId}/requests")
    public EventRequestStatusUpdateResult updateRequestStatusParticipateOwnerEvent(
            @PathVariable @NotNull @Min(1L) Long userId,
            @PathVariable @NotNull @Min(1L) Long eventId,
            @Valid @RequestBody EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        return participationRequestService.updateRequestStatusParticipateOwnerEvent(userId, eventId, eventRequestStatusUpdateRequest);
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getRequestsForOwnerEvent(
            @PathVariable @NotNull @Min(1L) Long userId,
            @PathVariable @NotNull @Min(1L) Long eventId) {
        return participationRequestService.getRequestsForOwnerEvent(userId, eventId);
    }
}
