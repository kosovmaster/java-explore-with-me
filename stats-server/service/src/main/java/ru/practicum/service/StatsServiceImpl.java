package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.EndpointHitDto;
import ru.practicum.ViewStatsDto;
import ru.practicum.exceptions.EndTimeBeforeStartTimeException;
import ru.practicum.mapper.StatsMapper;
import ru.practicum.model.EndpointHit;
import ru.practicum.model.ViewStats;
import ru.practicum.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statsRepository;
    private final StatsMapper statsMapper;

    @Transactional
    @Override
    public EndpointHitDto createHit(EndpointHitDto endpointHitDto) {
        EndpointHit endpointHitSave = statsRepository.save(statsMapper.toEndpointHit(endpointHitDto));
        return statsMapper.toEndpointHitDto(endpointHitSave);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        checkTime(start, end);
        List<ViewStats> viewStats;

        if (uris == null || uris.isEmpty()) {
            viewStats = Boolean.TRUE.equals(unique)
                    ? statsRepository.findAllByDateBetweenAndUniqueIp(start, end)
                    : statsRepository.findAllByDateBetweenStartAndEnd(start, end);
        } else {
            viewStats = Boolean.TRUE.equals(unique)
                    ? statsRepository.findAllByDateBetweenAndUriAndUniqueIp(start, end, uris)
                    : statsRepository.findAllByDateBetweenAndUri(start, end, uris);
        }
        return statsMapper.toViewStatsDtoList(viewStats);
    }

    private void checkTime(LocalDateTime start, LocalDateTime end) {
        if (end.isBefore(start) || end.equals(start)) {
            throw new EndTimeBeforeStartTimeException("End time cannot be before than start time");
        }
    }
}
