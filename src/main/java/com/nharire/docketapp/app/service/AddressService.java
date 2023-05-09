package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.dto.AddressDTO;
import com.nharire.docketapp.app.repository.AddressRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AddressService {

    AddressDTO saveAddressDetails(AddressDTO addressDTO);


    List<AddressDTO> getAddressDetails(Long id);

    void deleteAddressById(Long Id);

    AddressDTO updateAddressDetails(AddressDTO addressDTO);








}
