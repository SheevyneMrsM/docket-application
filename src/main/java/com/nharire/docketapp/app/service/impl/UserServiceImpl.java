package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.User;
import com.nharire.docketapp.app.model.dto.UserDTO;
import com.nharire.docketapp.app.repository.UserRepo;
import com.nharire.docketapp.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User saveUserDetails(UserDTO userDTO) {
        log.info("SAVE USER DETAILS: {}", userDTO.toString());
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        log.info("Saving user details: {}",user);
        return userRepo.save(user);
    }

    @Override
    public UserDTO updateUserService(UserDTO UserDTO) {
        return null;
    }

    @Override
    public void deleteUser(String nationalId) {

    }

    @Override
    public void deleteReport(Long id) {

    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public Optional<UserDTO> getUser(String nationalId) {
        return Optional.empty();
    }

    @Override
    public UserDTO addUser(User user) {
        return null;
    }
}
