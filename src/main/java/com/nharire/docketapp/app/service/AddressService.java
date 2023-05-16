package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.dto.AddressDTO;
import com.nharire.docketapp.app.repository.AddressRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AddressService {

    Address saveAddressDetails(AddressDTO addressDTO);


    Optional<Address> getAddressDetails(Long id);

    void deleteAddressById(Long id);

    AddressDTO updateAddressDetails(AddressDTO addressDTO);








}
