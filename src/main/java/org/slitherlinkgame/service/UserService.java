package org.slitherlinkgame.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.slitherlinkgame.dto.request.ScoreRequest;
import org.slitherlinkgame.dto.request.UserRequest;


import org.slitherlinkgame.dto.response.UserResponse;


import org.slitherlinkgame.entity.User;
import org.slitherlinkgame.mapper.UserMapper;
import org.slitherlinkgame.repository.UserJpaRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserJpaRepository userJpaRepository;
    private UserMapper userMapper;


    @Caching(evict = {
            @CacheEvict(value = "users", allEntries = true)
    })
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.fromRequest(request);
        return userMapper.toResponse(userJpaRepository.save(user));
    }

    @Cacheable(value = "user")
    public UserResponse findUserById(long id) {
        return userMapper.toResponse(findUserEntityById(id));
    }

    @Cacheable(value = "users")
    public List<UserResponse> findAll() {
        return userJpaRepository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Cacheable(value = "user")
    public UserResponse findUserByEmail(String email){
        return userMapper.toResponse(userJpaRepository.findDistinctByEmail(email).orElseThrow());
    }


    public void deleteUserById(long id) {
        User user = findUserEntityById(id);
        userJpaRepository.delete(user);
    }


    private User findUserEntityById(long id) {
        return userJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Score with id " + id + " not found"));
    }
}
