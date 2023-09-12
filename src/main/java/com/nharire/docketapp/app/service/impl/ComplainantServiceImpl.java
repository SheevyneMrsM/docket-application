package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.*;
import com.nharire.docketapp.app.model.dto.ComplainantDTO;
import com.nharire.docketapp.app.model.dto.response.ComplainantResponse;
import com.nharire.docketapp.app.repository.AddressRepo;
import com.nharire.docketapp.app.repository.ComplainantRepo;
import com.nharire.docketapp.app.repository.CrimeRegisterRepo;
import com.nharire.docketapp.app.service.ComplainantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class ComplainantServiceImpl implements ComplainantService {

    private final ComplainantRepo complainantRepo;

    private final AddressRepo addressRepo;
    private final CrimeRegisterRepo crimeRegisterRepo;
    @Override

    public ComplainantResponse saveComplainantDetails(ComplainantDTO complainantDTO) {

        ComplainantResponse complainantResponse = new ComplainantResponse();

        try {
            log.info("SAVE COMPLAINANT DETAILS: {}", complainantDTO.toString());
            //create new address object
            Address address = new Address();
            if (complainantDTO != null) {
                if (complainantDTO.getAddress() != null) {
                    //get address details from dto
                    BeanUtils.copyProperties(complainantDTO.getAddress(),address);
                    //save address to db
                    address = addressRepo.saveAndFlush(address);


                } else {
                    complainantResponse.setResponseCode(400);
                    complainantResponse.setDescription("No Address Details Found!!!");
                    complainantResponse.setMessage("Please kindly add Address details");
                    complainantResponse.setCode("DM-ADD-001");
                    return complainantResponse;
                }
            }

            //create new complainant object
            Complainant complainant = new Complainant();
            //set address into complainant
            complainant.setAddress(address);
            //copy complainant details from dto to complainant
            BeanUtils.copyProperties(complainantDTO, complainant);
            //print complainant details to the console
            log.info("Saving complainant details: {}", complainant);
           try {
               complainant = complainantRepo.saveAndFlush(complainant);
               //copy complainant into response
               BeanUtils.copyProperties(complainant,complainantResponse);
               complainantResponse.setResponseCode(200);
               complainantResponse.setMessage("SUCCESS");
               return complainantResponse;

           }catch (Exception e){
               complainantResponse.setResponseCode(500);
               complainantResponse.setDescription("FAILED TO SAVE COMPLAINANT");
               complainantResponse.setMessage("failed to save complainant");
               complainantResponse.setCode("DM-COMP-001");
           }

        }catch (Exception exception){
            log.info("FAILED TO SAVE COMPLAINANT, DATABASE ERROR " + exception);
            complainantResponse.setResponseCode(400);
            complainantResponse.setMessage("Failed to Save Information to Database");
            complainantResponse.setCode("DM-DB-001");
            complainantResponse.setDescription(exception.getMessage());
        }
        return complainantResponse;
    }




    @Override
    public ComplainantResponse updateComplainantDetails(ComplainantDTO complainantDTO) {
        //create new response object
        ComplainantResponse complainantResponse = new ComplainantResponse();
        try {


            log.info("UPDATING COMPLAINANT DETAILS:{}", complainantDTO.toString());
            //check if complainant is in the db
            Optional<Complainant> complainant = complainantRepo.findByNationalIdEqualsIgnoreCase(complainantDTO.getNationalId());
            //create new complainant object
            Complainant complainant1 = new Complainant();
            //check if complainant is present
            if (complainant.isPresent()) {
                //get complainant
                complainant1 = complainant.get();
                //copy dto properties to complainant1
                BeanUtils.copyProperties(complainantDTO, complainant1);
            }
            Optional<CrimeRegister> crimeRegister = crimeRegisterRepo.findById(complainantDTO.getCrimeId());
            if (crimeRegister.isPresent()) {
                CrimeRegister crimeRegister1 = crimeRegister.get();

                if (crimeRegister1.getComplainantNationalId() != null) {
                    BeanUtils.copyProperties(crimeRegister1.getComplainantNationalId(), crimeRegister1);
                }
                crimeRegister1.setComplainantNationalId(String.valueOf(complainant1));
                try {
                    //save crime register in db
                    crimeRegister1 = crimeRegisterRepo.saveAndFlush(crimeRegister1);
                } catch (Exception exception) {
                    //send response
                    complainantResponse.setMessage("Failed to Save Crime register database issues");
                }

                //return successful response
                complainantResponse.setCrimeRegister(crimeRegister1);
                //copy properties from accused to response
                BeanUtils.copyProperties(complainant1, complainantResponse);
                complainantResponse.setMessage("SUCCESS");
                complainantResponse.setResponseCode(200);
                return complainantResponse;

            } else {
                complainantResponse.setResponseCode(400);
                complainantResponse.setMessage("No complainant with this Id is registered");
                complainantResponse.setCode("DB-COMP-002");
                complainantResponse.setDescription("Failed to update complainant to crime register");
                BeanUtils.copyProperties(complainant1, complainantResponse);
                return complainantResponse;

            }

        }catch (Exception exception){
            log.info("FAILED TO SAVE COMPLAINANT, DATABASE ERROR " + exception);
            complainantResponse.setResponseCode(400);
            complainantResponse.setMessage("Failed to Save Information to Database");
            complainantResponse.setCode("DM-DB-001");
            complainantResponse.setDescription(exception.getMessage());
        }
        return complainantResponse;

    }

    @Override
    public Complainant addNextOfKin(NextOfKin nextOfKin) {
        Complainant complainant = complainantRepo.getById(nextOfKin.getNationalId());
        complainant.getNextOfKinNationalId();
        return complainantRepo.save(complainant);
    }

    @Override
    public Complainant deleteComplainantById(String nationalId) {
        complainantRepo.deleteById(nationalId);

        return null;
    }

    @Override
    public Optional<Complainant> getComplainantDetails(String nationalId) {
       return complainantRepo.findById(nationalId);
    }

    @Override
    public List<Complainant> getAllComplainantDetails() {
        return complainantRepo.findAll();
    }

    @Override
    public List<Complainant> getDateOfComplaint(String dateReported) {
        return complainantRepo.getDateOfComplaint(dateReported);
    }

    @Override
    public Complainant addWitnessDetails(Witness witness) {
        Complainant complainant = complainantRepo.getById(witness.getComplainant().getNationalId());
        complainant.getWitness().add(witness);
        return complainantRepo.save(complainant);
    }
}
