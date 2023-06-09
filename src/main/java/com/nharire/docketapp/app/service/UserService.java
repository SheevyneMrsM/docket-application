package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.User;
import com.nharire.docketapp.app.model.dto.UserDTO;
import com.nharire.docketapp.app.model.dto.response.UserResponse;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
     UserResponse saveUserDetails(UserDTO userDTO);

     UserDTO updateUserService(UserDTO userDTO);

     void deleteUser(String nationalId);

     void deleteReport(Long id);

     List<User> getAllUsers();

     Optional<User> getUser(String nationalId);





}
