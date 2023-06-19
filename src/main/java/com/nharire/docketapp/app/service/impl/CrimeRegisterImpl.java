package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.*;
import com.nharire.docketapp.app.model.dto.CrimeRegisterDTO;
import com.nharire.docketapp.app.model.dto.OfficerDTO;
import com.nharire.docketapp.app.model.dto.response.CrimeRegisterResponse;
import com.nharire.docketapp.app.repository.AccusedRepo;
import com.nharire.docketapp.app.repository.AddressRepo;
import com.nharire.docketapp.app.repository.CrimeRegisterRepo;
import com.nharire.docketapp.app.repository.OfficerRepo;
import com.nharire.docketapp.app.service.CrimeRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CrimeRegisterImpl implements CrimeRegisterService {
    private final CrimeRegisterRepo crimeRegisterRepo;
    private final OfficerRepo officerRepo;

    private final AddressRepo addressRepo;

    @Override
    public CrimeRegisterResponse saveCrimeRegisterDetails(CrimeRegisterDTO crimeRegisterDTO) {

        //create object for crime register response
        CrimeRegisterResponse crimeRegisterResponse = new CrimeRegisterResponse();
        try {
            //printing crime register details to the console
            log.info("SAVE CRIME REGISTER DETAILS: {}", crimeRegisterDTO);
            //create new Address object
            Address address = new Address();
            if (crimeRegisterDTO != null) {
                if (crimeRegisterDTO.getHeadId() != null) {
                    if (crimeRegisterDTO.getHeadId().getAddress() != null) {
                        // get address details from dto
                        BeanUtils.copyProperties(crimeRegisterDTO.getHeadId().getAddress(), address);
                        //save details in the db
                        address = addressRepo.saveAndFlush(address);
                    }else{
                        crimeRegisterResponse.setResponseCode(400);
                        crimeRegisterResponse.setDescription(" Please Add Address Details ");
                        crimeRegisterResponse.setMessage("Please kindly add Address Details");
                        crimeRegisterResponse.setCode("DM-ADD-001");
                        return  crimeRegisterResponse;

                    }
                }else{

                    crimeRegisterResponse.setResponseCode(400);
                    crimeRegisterResponse.setDescription("Request Failed on Officer Details Missing ");
                    crimeRegisterResponse.setMessage(" Please kindly include Officer details");
                    crimeRegisterResponse.setCode("DM-OFF-001");
                    return  crimeRegisterResponse;
                }
            }

            // create new object and
            Officer officer = new Officer();
            //set address to officer
            officer.setAddress(address);
            if (crimeRegisterDTO != null) {
                if (crimeRegisterDTO.getHeadId() != null) {
                    // get details from dto
                    BeanUtils.copyProperties(crimeRegisterDTO.getHeadId(), officer);
                }
            }

            //save details in the db
            Officer officer1 = officerRepo.saveAndFlush(officer);
            //create new crime register object
            CrimeRegister crimeRegister = new CrimeRegister();
            //set officer to crime register
            crimeRegister.setHeadId(officer1);
            // copy properties from dto to crime register
            BeanUtils.copyProperties(crimeRegisterDTO, crimeRegister);
            //print crime register details to the console
            log.info("Saving crime register details: {}", crimeRegister);
            // save crime register details to db
            try {
                crimeRegister = crimeRegisterRepo.saveAndFlush(crimeRegister);
            } catch (Exception ex) {
                crimeRegisterResponse.setDescription("FAILED TO SAVE CRIME REGISTER");
                crimeRegisterResponse.setResponseCode(500);
                crimeRegisterResponse.setMessage("failed to save crime register");
            }
            //copy crime register into response
            BeanUtils.copyProperties(crimeRegister, crimeRegisterResponse);
            crimeRegisterResponse.setResponseCode(200);
            crimeRegisterResponse.setMessage("SUCCESS");

        } catch (Exception exception) {
            log.info("FAILED TO SAVE DOCKET, DATABASE ERROR " + exception);
            crimeRegisterResponse.setResponseCode(400);
            crimeRegisterResponse.setMessage("Failed to Save Information to Database");
            crimeRegisterResponse.setCode("DM-DB-001");
            crimeRegisterResponse.setDescription(exception.getMessage());
        }
        return crimeRegisterResponse;

    }

    @Override
    public CrimeRegisterDTO updateCrimeRegisterDetails(CrimeRegisterDTO crimeRegisterDTO) {
        Optional<CrimeRegister> crimeRegister = crimeRegisterRepo.findById(crimeRegisterDTO.getCrimeId());
        CrimeRegister crimeRegister1;
        if (crimeRegister.isPresent()) {
            crimeRegister1 = crimeRegister.get();
            BeanUtils.copyProperties(crimeRegisterDTO, crimeRegister1);
        } else {
            throw new RuntimeException("No details found ,cant update!!!");
        }
        BeanUtils.copyProperties(crimeRegister1, crimeRegisterDTO);
        return crimeRegisterDTO;
    }

    @Override
    public void deleteCrimeRegisterById(Long crimeId) {
        crimeRegisterRepo.deleteById(crimeId);

    }

    @Override
    public void deleteAccusedDetailsById(Long id) {
        crimeRegisterRepo.deleteById(id);

    }

    @Override
    public void deleteComplainantDetailsById(Long id) {
        crimeRegisterRepo.deleteById(id);

    }

    @Override
    public CrimeRegister addAccused(Accused accused) {
        //created a field named crimeId
        Long crimeId = 0L;
        //check if crime is not equals to null
        if (accused.getCrime() != null) {
            //check if crimeId  is not equal to null
            if (accused.getCrime().getCrimeId() != null) {
                //if its not null assign to crimeId
                crimeId = accused.getCrime().getCrimeId();
                //get crime register from db
                //assign crime register from db to obj
                CrimeRegister crimeRegister = crimeRegisterRepo.getById(crimeId);
                //create a new accusedList
                List<Accused> accusedList = new ArrayList<>();
                //add accused to accusedList
                accusedList.add(accused);
                //check if crime register is not equal to null
                if (crimeRegister != null) {
                    //set Accused list to crime register
                    crimeRegister.setAccused(accusedList);
                } else {
                    log.info("No crime is registered for this individual");
                }

                //save crime register to db
                return crimeRegisterRepo.save(crimeRegister);

            }
        }

        return new CrimeRegister();

    }

    @Override
    public CrimeRegister addComplainant(Complainant complainant) {
        CrimeRegister crimeRegister = crimeRegisterRepo.getById(complainant.getCrime().getCrimeId());
        crimeRegister.getComplainer().getCrime();
        return crimeRegisterRepo.save(crimeRegister);
    }

    @Override
    public List<CrimeRegister> getAllCrimeRegisterDetails() {
        return crimeRegisterRepo.findAll();
    }

    @Override
    public Optional<CrimeRegister> getCrimeRegisterDetails(Long crimeId) {
        return crimeRegisterRepo.findById(crimeId);
    }

    @Override
    public List<CrimeRegister> getAllByDateOfReport(Date dateOfReport) {
        return crimeRegisterRepo.getAllByDateOfReport(dateOfReport);
    }
}
