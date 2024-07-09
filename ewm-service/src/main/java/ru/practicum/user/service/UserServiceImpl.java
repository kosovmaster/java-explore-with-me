package ru.practicum.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.exception.NotFoundException;
import ru.practicum.user.dto.NewUserRequest;
import ru.practicum.user.dto.UserDto;
import ru.practicum.user.mapper.UserMapper;
import ru.practicum.user.model.User;
import ru.practicum.user.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDto createUser(NewUserRequest newUserRequest) {
        User createdUser = userRepository.save(userMapper.toUser(newUserRequest));
        return userMapper.toUserDto(createdUser);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        getExceptionIfUserNotFound(userId);
        userRepository.deleteById(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getUser(List<Long> ids, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        if (ids != null && !ids.isEmpty()) {
            List<User> userList = userRepository.findAllByIdIn(ids, pageable);
            return userMapper.toUserDtoList(userList);
        }
        List<User> allUserList = userRepository.findAll(pageable).getContent();
        return userMapper.toUserDtoList(allUserList);
    }

    private void getExceptionIfUserNotFound(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User with id=" + userId + " was not found",
                    Collections.singletonList("User id does not exist"));
        }
    }
}
