package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.User;
import com.nharire.docketapp.app.model.dto.UserDTO;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
     User saveUserDetails(UserDTO userDTO);

     UserDTO updateUserService(UserDTO UserDTO);

     void deleteUser(String nationalId);

     void deleteReport(Long id);

     List<UserDTO> getAllUsers();

     Optional<UserDTO> getUser(String nationalId);

     UserDTO addUser(User user);



}
