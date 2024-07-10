package ru.practicum.participation.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.participation.dto.ParticipationRequestDto;
import ru.practicum.participation.model.ParticipationRequest;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.constant.Constant.FORMATTER;

@Component
public class ParticipationRequestMapper {
    public ParticipationRequestDto toParticipationRequestDto(ParticipationRequest participationRequest) {
        if (participationRequest == null) {
            return null;
        }
        return ParticipationRequestDto.builder()
                .id(participationRequest.getId())
                .created(participationRequest.getCreated().format(FORMATTER))
                .event(participationRequest.getEvent())
                .requester(participationRequest.getRequester())
                .status(participationRequest.getStatus())
                .build();
    }

    public List<ParticipationRequestDto> toParticipationRequestDtoList(List<ParticipationRequest> participationRequestList) {
        if (participationRequestList == null) {
            return Collections.emptyList();
        }
        return participationRequestList.stream()
                .map(this::toParticipationRequestDto)
                .collect(Collectors.toList());
    }
}
