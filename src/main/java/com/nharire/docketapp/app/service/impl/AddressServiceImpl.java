package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.dto.AddressDTO;
import com.nharire.docketapp.app.repository.AddressRepo;
import com.nharire.docketapp.app.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepo addressRepo;

    @Override
    public Address saveAddressDetails(AddressDTO addressDTO) {
        log.info("ADDRESS DETAILS: {}", addressDTO.toString());
        Address address = new Address();
        BeanUtils.copyProperties(addressDTO,address);
        log.info("Saving address details: {}",address);
        address =addressRepo.save(address);
        BeanUtils.copyProperties(address,addressDTO);
        return address;

    }

    @Override
    public Optional<Address> getAddressDetails(Long id) {
        return addressRepo.findById(id);

    }

    @Override
    public void deleteAddressById(Long id) {
        addressRepo.deleteById(id);

    }

    @Override
    public AddressDTO updateAddressDetails(AddressDTO addressDTO) {

        return null;
    }
}
