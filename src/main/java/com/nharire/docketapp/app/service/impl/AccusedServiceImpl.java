package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Accused;
import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.CrimeRegister;
import com.nharire.docketapp.app.model.NextOfKin;
import com.nharire.docketapp.app.model.dto.AccusedDTO;
import com.nharire.docketapp.app.model.dto.response.AccusedResponse;
import com.nharire.docketapp.app.repository.AccusedRepo;
import com.nharire.docketapp.app.repository.AddressRepo;
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

    private final AddressRepo addressRepo;

    @Override
    public AccusedResponse saveAccusedDetails(AccusedDTO accusedDTO) {

        AccusedResponse accusedResponse = new AccusedResponse();

        try {
            // printing accused details to the console
            log.info("ACCUSED DETAILS: {}", accusedDTO.toString());
            //create new address object
            Address address = new Address();
            //check if accused is not equal to null
            if (accusedDTO != null) {

                //check if we get address from accused is not equal to null

                if (accusedDTO.getAddress() != null) {
                    //now copy properties from dto to address
                    BeanUtils.copyProperties(accusedDTO.getAddress(), address);
                    //save address to database
                    address = addressRepo.saveAndFlush(address);
                } else {
                    //if address is null get a response message that no details are found, & they must be added
                    accusedResponse.setResponseCode(400);
                    accusedResponse.setDescription("No Address Details Found!!!");
                    accusedResponse.setMessage("Please kindly add Address details");
                    accusedResponse.setCode("DM-ADD-001");
                    //adding return because address should be saved in accused no matter what
                    return accusedResponse;
                }
            }
            //creating new accused object
            Accused accused = new Accused();
            // now set address in accused ***nb (the address we saved above )
            accused.setAddress(address);
            //now copy properties from dto to accused
            BeanUtils.copyProperties(accusedDTO, accused);
            //print accused details to the console
            log.info("Saving accused details: {}", accused);
            try {
                /*
                * wraping this in a try because it might throw an exception
                * saving accused to database*/
                accused = accusedRepo.saveAndFlush(accused);
                //copy accused into response
                BeanUtils.copyProperties(accused, accusedResponse);
                //if we successfully copied accused to response then it should print 200 meaning success
                accusedResponse.setResponseCode(200);
                accusedResponse.setMessage("SUCCESS");
                return accusedResponse;

            } catch (Exception e) {
                //if say an exception is thrown then a response message saying failed to save accused should be printed
                accusedResponse.setResponseCode(500);
                accusedResponse.setDescription("FAILED TO SAVE ACCUSED");
                accusedResponse.setMessage("failed to save ACCUSED");
                accusedResponse.setCode("DM-ACC-001");
            }

        } catch (Exception exception) {
            //also if we fail to save both accused and save address into accused then it should give us messages below
            log.info("FAILED TO SAVE ACCUSED, DATABASE ERROR " + exception);
            accusedResponse.setResponseCode(400);
            accusedResponse.setMessage("Failed to Save Information to Database");
            accusedResponse.setCode("DM-DB-001");
            accusedResponse.setDescription(exception.getMessage());
        }
        return accusedResponse;
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
        //printing to the console
        log.info("ADDING ACCUSED TO CRIME " + accusedDTO.toString());
        //check if accused is present
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

        Optional<CrimeRegister> crimeRegister = crimeRegisterRepo.findById(accusedDTO.getCrimeId());

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
                accusedResponse.setMessage("Failed to Save Crime register database issues");
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
