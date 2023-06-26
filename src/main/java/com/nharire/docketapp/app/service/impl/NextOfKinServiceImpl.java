package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Accused;
import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.Complainant;
import com.nharire.docketapp.app.model.NextOfKin;
import com.nharire.docketapp.app.model.dto.NextOfKinDTO;
import com.nharire.docketapp.app.model.dto.response.NextOfKinResponse;
import com.nharire.docketapp.app.repository.AccusedRepo;
import com.nharire.docketapp.app.repository.AddressRepo;
import com.nharire.docketapp.app.repository.NextOfKinRepo;
import com.nharire.docketapp.app.service.NextOfKinService;
import com.nharire.docketapp.exceptions.AccusedNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class NextOfKinServiceImpl implements NextOfKinService {
    private final NextOfKinRepo nextOfKinRepo;
    private final AccusedRepo accusedRepo;

    private final AddressRepo addressRepo;

    @Override
    public NextOfKinResponse saveNextOfKinDetails(NextOfKinDTO nextOfKinDTO) {
        NextOfKinResponse nextOfKinResponse = new NextOfKinResponse();
        try {
            log.info("SAVE NEXT OF KIN DETAILS: {}", nextOfKinDTO.toString());
            //create new address object
            Address address = new Address();
            if (nextOfKinDTO != null) {
                if (nextOfKinDTO.getAddress() != null) {
                    //get address details from dto
                    BeanUtils.copyProperties(nextOfKinDTO.getAddress(),address);

                } else {
                    nextOfKinResponse.setResponseCode(400);
                    nextOfKinResponse.setDescription("No Address Details Found!!!");
                    nextOfKinResponse.setMessage("Please kindly add Address details");
                    nextOfKinResponse.setCode("DM-ADD-001");
                    return nextOfKinResponse;
                }
            }
            //save address to db
            Address address1 = addressRepo.saveAndFlush(address);
            //create Accused  object
            Address address2= new Address();
        if (nextOfKinDTO!= null) {
            if (nextOfKinDTO.getAccused()!= null) {
                if (nextOfKinDTO.getAccused().getAddress() != null)
                    //get address details from dto
                    BeanUtils.copyProperties(nextOfKinDTO.getAccused().getAddress(), address2);
                    address = addressRepo.saveAndFlush(address2);
            }else{
                nextOfKinResponse.setResponseCode(400);
                nextOfKinResponse.setDescription(" Please Add Address Details ");
                nextOfKinResponse.setMessage("Please kindly add Address Details");
                nextOfKinResponse.setCode("DM-ADD-001");
                return  nextOfKinResponse;
            }

            }else {
            nextOfKinResponse.setResponseCode(400);
            nextOfKinResponse.setDescription("Request Failed on Accused Details Missing ");
            nextOfKinResponse.setMessage(" Please kindly include Accused details");
            nextOfKinResponse.setCode("DM-ACC-001");
            return  nextOfKinResponse;

        }
        Accused accused = new Accused();
        accused.setAddress(address);

        if (nextOfKinDTO != null){
            if (nextOfKinDTO.getAccused()!= null){
                BeanUtils.copyProperties(nextOfKinDTO.getAccused(),accused);
            }
        }
          Accused accused1 = accusedRepo.saveAndFlush(accused);

        List<Complainant> complainantList = new ArrayList<>();
            Address address3 = new Address();
                for (Complainant complainant:complainantList) {
                    if(complainant != null){
                        if (complainant.getNextOfKin()!= null){
                            if (complainant.getNextOfKin().getAddress() != null){
                                BeanUtils.copyProperties(complainant.getNextOfKin().getAddress(),address3);
                               address= addressRepo.saveAndFlush(address3);

                        }
                        }
                    }

            }

        NextOfKin nextOfKin = new NextOfKin();
         nextOfKin.setAccused(accused1);
         nextOfKin.setAddress(address1);
        log.info("Saving accused details: {}", nextOfKin);
        try {
            nextOfKin  = nextOfKinRepo.saveAndFlush(nextOfKin);
        } catch (Exception ex) {
            nextOfKinResponse.setDescription("FAILED TO SAVE NEXT OF KIN");
            nextOfKinResponse.setResponseCode(500);
            nextOfKinResponse.setMessage("failed to next of kin");
            nextOfKinResponse.setCode("DM-NOK-001");
        }
            BeanUtils.copyProperties(nextOfKin,nextOfKinResponse);
            nextOfKinResponse.setResponseCode(200);
            nextOfKinResponse.setMessage("SUCCESS");
        }catch (Exception exception){
            log.info("FAILED TO SAVE NEXT OF KIN, DATABASE ERROR " + exception);
            nextOfKinResponse.setResponseCode(400);
            nextOfKinResponse.setMessage("Failed to Save Information to Database");
            nextOfKinResponse.setCode("DM-DB-001");
            nextOfKinResponse.setDescription(exception.getMessage());
        }

        return nextOfKinResponse;
    }


    @Override
    public NextOfKinDTO updateNextOfKinDetails(NextOfKinDTO nextOfKinDTO) {
        Optional<NextOfKin> nextOfKin = nextOfKinRepo.findById(nextOfKinDTO.getNationalId());
        NextOfKin nextOfKin1;
        if (nextOfKin.isPresent()) {
            nextOfKin1 = nextOfKin.get();
            BeanUtils.copyProperties(nextOfKinDTO, nextOfKin1);
        } else {
            throw new RuntimeException("Oops No details found, cant update!!!");
        }
        BeanUtils.copyProperties(nextOfKin1, nextOfKinDTO);

        return nextOfKinDTO;
    }

    @Override
    public void deleteNextOfKinById(String nationalId) {
        nextOfKinRepo.deleteById(nationalId);

    }

    @Override
    public void deleteAddressDetails(Long id) {
        nextOfKinRepo.deleteById(String.valueOf(id));

    }

    @Override
    public List<NextOfKin> getAllNextOfKinDetails() {

        return nextOfKinRepo.findAll();
    }

    @Override
    public Optional<NextOfKin> getNextOfKinDetails(String nationalId) {

        return nextOfKinRepo.findById(nationalId);
    }



    @Override
    public NextOfKin addAddressDetails(Address address) {

        return nextOfKinRepo.addAddressDetails(address);
    }
}
