package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.Complainant;
import com.nharire.docketapp.app.model.dto.AddressDTO;
import com.nharire.docketapp.app.model.dto.response.AddressResponse;
import com.nharire.docketapp.app.repository.AddressRepo;
import com.nharire.docketapp.app.repository.ComplainantRepo;
import com.nharire.docketapp.app.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepo addressRepo;
    private final ComplainantRepo complainantRepo;

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
    public Address deleteAddressById(Long id) {
        addressRepo.deleteById(id);

        return null;
    }

    @Override
    public AddressResponse updateAddressDetails(AddressDTO addressDTO) {
        AddressResponse addressResponse = new AddressResponse();

        //print address detail to the console
        try {


            log.info("ADDING Address DETAILS TO COMPLAINANT" + addressDTO.toString());
            List<Address> address = addressRepo.findByStreetAddressEqualsIgnoreCase(addressDTO.getStreetAddress());
            //create new address object
            Address address1 = new Address();
            //check if address id present
            if (address != null){
                if(!address.isEmpty()) {
                    address1 = address.get(0);
                    //copy properties from address dto to address
                    BeanUtils.copyProperties(addressDTO, address1);
                    //save address to db

//                    Optional<Complainant> complainant = complainantRepo.findByNationalIdEqualsIgnoreCase(String.valueOf(addressDTO.getId()));
//                    if (complainant.isPresent()){
//                        Complainant complainant1 = complainant.get();
//                        complainant1.setAddress(address1);
//                        try {
//                            complainant1 = complainantRepo.saveAndFlush(complainant1);
//                        }catch (Exception e){
//                            addressResponse.setMessage("could not save complainant");
//                        }
//                        addressResponse.setComplainant(complainant1);
//                        BeanUtils.copyProperties(complainant1,addressResponse);
//                        BeanUtils.copyProperties(address1,addressResponse);
//                        addressResponse.setMessage("SUCCESS");
//                        addressResponse.setResponseCode(200);
//                    }else{
//                        //BeanUtils.copyProperties(address1, addressResponse);
//                        addressResponse.setMessage("Error, failed to get complainant with national id "+ addressDTO.getId());
//                        addressResponse.setResponseCode(400);
//                        addressResponse.setDescription("Please kindly provide a valid national id");
//                        return addressResponse;
//                    }
//                }else {
//
//                    BeanUtils.copyProperties(addressDTO,address1);
//                    address1 = addressRepo.saveAndFlush(address1);
//                    BeanUtils.copyProperties(address1,addressResponse);
//                    Optional<Complainant> complainant = complainantRepo.findByNationalIdEqualsIgnoreCase(addressDTO.getNationalId());
//                    if (complainant.isPresent()) {
//                        Complainant complainant1 = complainant.get();
//                        complainant1.setAddress(address1);
//                        complainant1 = complainantRepo.saveAndFlush(complainant1);
//                        BeanUtils.copyProperties(complainant1, addressResponse);
//                        //BeanUtils.copyProperties(address1, addressResponse);
//                        addressResponse.setMessage("SUCCESS");
                        addressResponse.setResponseCode(200);
                       addressResponse.setMessage("SUCCESS");
                        addressResponse.setResponseCode(200);
                        return addressResponse;
//                    }
                }
            }
        }catch (Exception exception){
            log.info("Failed to connect to db" + exception.getMessage());
            addressResponse.setResponseCode(400);
            addressResponse.setMessage("No address with this street address is registered");
            addressResponse.setCode("DB-Add-002");
            addressResponse.setDescription("Failed to update address to complainant");
            BeanUtils.copyProperties(addressDTO, addressResponse);
            return addressResponse;

        }

        return addressResponse;
    }

}
