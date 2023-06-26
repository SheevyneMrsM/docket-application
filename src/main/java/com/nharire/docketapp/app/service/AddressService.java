package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.dto.AddressDTO;
import com.nharire.docketapp.app.model.dto.response.AddressResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AddressService {

    Address saveAddressDetails(AddressDTO addressDTO);


    Optional<AddressResponse> getAddressDetails(Long id);

    void deleteAddressById(Long id);

    AddressResponse updateAddressDetails(AddressDTO addressDTO);








}
