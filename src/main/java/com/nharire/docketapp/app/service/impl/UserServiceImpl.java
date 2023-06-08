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
    public UserDTO updateUserService(UserDTO userDTO){
        Optional<User> user = userRepo.findById(userDTO.getNationalId());
        User user1;
        if (user.isPresent()){
            user1 = user.get();
            BeanUtils.copyProperties(userDTO,user1);
        }else {
            throw new RuntimeException("No user details found, cant update!!!");
        }
        BeanUtils.copyProperties(user1,userDTO);
        return userDTO;
    }

    @Override
    public void deleteUser(String nationalId) {
        userRepo.deleteById(nationalId);

    }

    @Override
    public void deleteReport(Long id) {
        userRepo.deleteById(String.valueOf(id));

    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> getUser(String nationalId) {
        return userRepo.findById(nationalId);
    }


}
