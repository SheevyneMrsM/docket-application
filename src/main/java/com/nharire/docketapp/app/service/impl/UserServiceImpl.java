package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.User;
import com.nharire.docketapp.app.model.dto.UserDTO;
import com.nharire.docketapp.app.model.dto.response.UserResponse;
import com.nharire.docketapp.app.repository.AddressRepo;
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
    private final AddressRepo addressRepo;

    @Override
    public UserResponse saveUserDetails(UserDTO userDTO) {
        //created a new user response object
        UserResponse userResponse = new UserResponse();
        try {
            //print user details to the console
            log.info("SAVE USER DETAILS: {}", userDTO.toString());
            Address address = new Address();
            if (userDTO != null) {
                if (userDTO.getAddress() != null) {
                    BeanUtils.copyProperties(userDTO.getAddress(), address);
                } else {
                    userResponse.setResponseCode(400);
                    userResponse.setDescription(" Please Add Address Details ");
                    userResponse.setMessage("Please kindly add Address Details");
                    userResponse.setCode("DM-ADD-001");
                    return userResponse;
                }
            }
            Address address1 = addressRepo.saveAndFlush(address);
            User user = new User();
            user.setAddress(address1);
            BeanUtils.copyProperties(userDTO, user);
            log.info("Saving user details: {}", user);
            try {
                user = userRepo.saveAndFlush(user);
            } catch (Exception exception) {
                userResponse.setDescription("FAILED TO SAVE USER DETAILS");
                userResponse.setResponseCode(500);
                userResponse.setMessage("failed to save user details");
            }
            BeanUtils.copyProperties(user,userResponse);
            userResponse.setMessage("SUCCESS");
            userResponse.setResponseCode(200);
        }catch (Exception e){
            log.info("FAILED TO SAVE USER TO DB, DATABASE ERROR " + e);
            userResponse.setResponseCode(400);
            userResponse.setMessage("Failed to Save Information to Db");
            userResponse.setCode("DM-DB-001");
            userResponse.setDescription(e.getMessage());
        }

        return userResponse;
    }

    @Override
    public
    UserResponse updateUserDetails(UserDTO userDTO){
        UserResponse userResponse = new UserResponse();
       try {
           log.info("UPDATING USER DETAILS : {}", userDTO.toString());

           Optional<User> user = userRepo.findById(userDTO.getNationalId());
           User user1 = new User();
           if (user.isPresent()) {
               user1 = user.get();
               BeanUtils.copyProperties(userDTO, user1);
           }
           Optional<Address> address = addressRepo.findByIdEquals(userDTO.getAddress().getId());
           if (address.isPresent()) {
               Address address1 = address.get();
               user1.setAddress(address1);
               try {
                   user1 = userRepo.saveAndFlush(user1);
               } catch (Exception exception) {
                   userResponse.setMessage("failed to save user database issues");
               }
               BeanUtils.copyProperties(user1, userResponse);
               userResponse.setResponseCode(200);
               userResponse.setMessage("SUCCESS");
           }

       }catch (Exception e){
           log.info("FAILED TO SAVE NEXT OF KIN" + e);
           userResponse.setResponseCode(500);
           userResponse.setMessage("failed to save next of kin information to database ");
           userResponse.setCode("DM-DB-001");
           userResponse.setDescription(e.getMessage());
       }

        return userResponse;
    }

    @Override
    public User deleteUser(String nationalId) {
        userRepo.deleteById(nationalId);

        return null;
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
