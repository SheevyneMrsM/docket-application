package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.dto.AddressDTO;
import com.nharire.docketapp.app.model.dto.response.AddressResponse;
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
    public Optional<AddressResponse> getAddressDetails(Long id) {
        AddressResponse addressResponse = new AddressResponse();
        Address address = new Address();
       if (address.getId() != null){
          Optional<Address> address1 =addressRepo.findById(id);
           BeanUtils.copyProperties(address1,addressResponse);
       }else {
           addressResponse.setDescription("failed to get address by id");
           addressResponse.setResponseCode(400);
           addressResponse.setMessage("FAILED TO GET ADDRESS BY ID, ENTER ADDRESS DETAILS");
       }

        return Optional.empty();

    }

    @Override
    public void deleteAddressById(Long id) {
        addressRepo.deleteById(id);

    }

    @Override
    public AddressDTO updateAddressDetails(AddressDTO addressDTO) {
        Optional<Address> address = addressRepo.findById(addressDTO.getId());
        Address address1;
        if (address.isPresent()){
            address1 = address.get();
            BeanUtils.copyProperties(addressDTO,address1);
            addressRepo.save(address1);
        }else{
            throw new RuntimeException("No details found, Cant update!!! ");
        }
        BeanUtils.copyProperties(address1,addressDTO);
        return addressDTO;
    }


}
