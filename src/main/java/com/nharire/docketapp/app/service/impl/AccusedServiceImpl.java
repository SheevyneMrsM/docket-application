package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Accused;
import com.nharire.docketapp.app.model.CrimeRegister;
import com.nharire.docketapp.app.model.NextOfKin;
import com.nharire.docketapp.app.model.dto.AccusedDTO;
import com.nharire.docketapp.app.model.dto.response.AccusedResponse;
import com.nharire.docketapp.app.repository.AccusedRepo;
import com.nharire.docketapp.app.repository.CrimeRegisterRepo;
import com.nharire.docketapp.app.service.AccusedService;
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
public class AccusedServiceImpl implements AccusedService {


    private final AccusedRepo accusedRepo;

    private final CrimeRegisterRepo crimeRegisterRepo;

    @Override
    public Accused saveAccusedDetails(AccusedDTO accusedDTO) {
        log.info("ACCUSED DETAILS: {}", accusedDTO.toString());
        Accused accused = new Accused();
        BeanUtils.copyProperties(accusedDTO,accused);
        log.info("Saving new accused details: {}", accused);
        return accusedRepo.save(accused);
    }


    @Override
    public Optional<Accused> getAccusedDetails(String nationalId) {
        return  accusedRepo.findById(nationalId);

    }

    @Override
    public List<Accused> getAllAccusedDetails() {
        return accusedRepo.findAll();
    }

    @Override
    public List<AccusedDTO> getAccusedByDateReported(String dateReported) {
        return accusedRepo.getAccusedByDateReported(dateReported);
    }

    @Override
    public void deleteById(String nationalId) {
        accusedRepo.deleteById(nationalId);

    }

    @Override
    public AccusedResponse updateAccusedDetails(AccusedDTO accusedDTO) {
        AccusedResponse accusedResponse = new AccusedResponse();

        log.info("ADDING ACCUSED TO CRIME " + accusedDTO.toString());
        Optional<Accused> accused = accusedRepo.findById(accusedDTO.getNationalId());
        //create new accused object
        Accused accused1 = new Accused();
        //check if accused is in the database
        if (accused.isEmpty()) {
            //copy properties from dto to accused 1
            BeanUtils.copyProperties(accusedDTO, accused1);
            //save accused in db
            accused1 = accusedRepo.saveAndFlush(accused1);

        } else {
            //assign accused to accused1
            accused1 = accused.get();
        }

        //check if crime register is present

        Optional<CrimeRegister> crimeRegister = crimeRegisterRepo.findById(Long.valueOf(accusedDTO.getCrimeId()));

        // if crime is there the add accused to accusedList
        if(crimeRegister.isPresent()) {
            //get crime register since it is present
            CrimeRegister crimeRegister1 = crimeRegister.get();

            //create list of accused
            List<Accused> accusedList = new ArrayList<>();

            //add existing accused to array
            if(crimeRegister1.getAccused()!=null){
                accusedList.addAll(crimeRegister1.getAccused());
            }
            //add accused to list
            accusedList.add(accused1);
            //set accused list to crime register
            crimeRegister1.setAccused(accusedList);

            try {
                //save crime register in db
                crimeRegister1 = crimeRegisterRepo.saveAndFlush(crimeRegister1);
            }catch (Exception exception){
                //send response
                accusedResponse.setMessage("Failed to Save Crime e=Register database issues");
            }

            //return successful response
            accusedResponse.setCrimeRegister(crimeRegister1);
            //copy properties from accused to response
            BeanUtils.copyProperties(accused1, accusedResponse);
            accusedResponse.setMessage("SUCCESS");
            accusedResponse.setResponseCode(200);
            return accusedResponse;
        } else {
            accusedResponse.setResponseCode(400);
            accusedResponse.setMessage("No crime with this Id is registered");
            accusedResponse.setCode("DB-ACC-002");
            accusedResponse.setDescription("Failed to update accused to crime register");
            BeanUtils.copyProperties(accused1, accusedResponse);
            return accusedResponse;

        }


    }


    @Override
    public Accused addNextOfKin(NextOfKin nextOfKin) {
        Accused accused = accusedRepo.getById(nextOfKin.getAccused().getNationalId());
        accused.getNextOfKin().add(nextOfKin);
        return accusedRepo.save(accused);
    }
}
