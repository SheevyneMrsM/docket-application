package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Accused;
import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.Complainant;
import com.nharire.docketapp.app.model.NextOfKin;
import com.nharire.docketapp.app.model.dto.NextOfKinDTO;
import com.nharire.docketapp.app.repository.AccusedRepo;
import com.nharire.docketapp.app.repository.AddressRepo;
import com.nharire.docketapp.app.repository.NextOfKinRepo;
import com.nharire.docketapp.app.service.NextOfKinService;
import com.nharire.docketapp.exceptions.AccusedNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class NextOfKinServiceImpl implements NextOfKinService {
    private  final NextOfKinRepo nextOfKinRepo;
    private final AddressRepo addressRepo;
    private final AccusedRepo accusedRepo;
    @Override
    public NextOfKin saveNextOfKinDetails(NextOfKinDTO nextOfKinDTO) {
        log.info("SAVE NEXT OF KIN DETAILS: {}",nextOfKinDTO.toString());
        NextOfKin nextOfKin = new NextOfKin();
        if (accusedRepo.existsById(nextOfKinDTO.getAccused().getNationalId())){
            Accused accused = accusedRepo.getById(nextOfKinDTO.getAccused().getNationalId());
            nextOfKin.setFirstName(nextOfKinDTO.getFirstName());
            nextOfKin.setEmail(nextOfKinDTO.getEmail());
            nextOfKin.setNationalId(nextOfKinDTO.getNationalId());
            nextOfKin.setPhoneNumber(nextOfKinDTO.getPhoneNumber());
            //nextOfKin.setAddress(nextOfKinDTO.getAddress());
            nextOfKin.setSurname(nextOfKinDTO.getSurname());
            nextOfKin.setRelationship(nextOfKinDTO.getRelationship());
            log.info("FETCHED ACCUSED DETAILS: {}", accused);
            nextOfKin.setAccused(nextOfKinDTO.getAccused());
            log.info("Saving next of kin details: {}",nextOfKin);
            nextOfKinRepo.save(nextOfKin);
        }else {
            throw new AccusedNotFoundException("No accused details for this next of kin, enter accused details first");
        }
        return nextOfKin;
    }

    @Override
    public NextOfKinDTO updateNextOfKinDetails(NextOfKinDTO nextOfKinDTO) {
        Optional<NextOfKin> nextOfKin = nextOfKinRepo.findById(nextOfKinDTO.getNationalId());
        NextOfKin nextOfKin1;
        if (nextOfKin.isPresent()){
            nextOfKin1= nextOfKin.get();
            BeanUtils.copyProperties(nextOfKinDTO,nextOfKin1);
        }else {
            throw new RuntimeException("Oops No details found, cant update!!!");
        }
        BeanUtils.copyProperties(nextOfKin1,nextOfKinDTO);

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
