package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.dto.AddressDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AddressService {

    AddressDTO saveAddressDetails(AddressDTO addressDTO);


    Optional<Address> getAddressDetails(Long id);

    void deleteAddressById(Long Id);

    AddressDTO updateAddressDetails(AddressDTO addressDTO);








}
