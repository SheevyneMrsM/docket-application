package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.Complainant;
import com.nharire.docketapp.app.model.NextOfKin;
import com.nharire.docketapp.app.model.Witness;
import com.nharire.docketapp.app.model.dto.ComplainantDTO;
import com.nharire.docketapp.app.model.dto.response.ComplainantResponse;
import com.nharire.docketapp.app.model.dto.response.CrimeRegisterResponse;
import com.nharire.docketapp.app.repository.AddressRepo;
import com.nharire.docketapp.app.repository.ComplainantRepo;
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

                } else {
                    complainantResponse.setResponseCode(400);
                    complainantResponse.setDescription("No Address Details Found!!!");
                    complainantResponse.setMessage("Please kindly add Address details");
                    complainantResponse.setCode("DM-ADD-001");
                    return complainantResponse;
                }
            }
            //save address to db
            Address address1 = addressRepo.saveAndFlush(address);
            //create new complainant object
            Complainant complainant = new Complainant();
            //set address into complainant
            complainant.setAddress(address1);
            //copy complainant details from dto to complainant
            BeanUtils.copyProperties(complainantDTO, complainant);
            //print complainant details to the console
            log.info("Saving complainant details: {}", complainant);
           try {
               complainant = complainantRepo.saveAndFlush(complainant);
           }catch (Exception e){
               complainantResponse.setResponseCode(500);
               complainantResponse.setDescription("FAILED TO SAVE COMPLAINANT");
               complainantResponse.setMessage("failed to save complainant");
               complainantResponse.setCode("DM-COMP-001");
           }
           //copy complainant into response
            BeanUtils.copyProperties(complainant,complainantResponse);
            complainantResponse.setResponseCode(200);
            complainantResponse.setMessage("SUCCESS");
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
    public ComplainantDTO updateComplainantDetails(ComplainantDTO complainantDTO) {
        Optional<Complainant> complainant = complainantRepo.findById(complainantDTO.getNationalId());
        Complainant complainant1;
        if (complainant.isPresent()){
            complainant1= complainant.get();
            BeanUtils.copyProperties(complainantDTO,complainant1);
        }else {
            throw new RuntimeException("Oops No details found, cant update!!!");
        }
        BeanUtils.copyProperties(complainant1,complainantDTO);

        return complainantDTO;
    }

    @Override
    public Complainant addNextOfKin(NextOfKin nextOfKin) {
        Complainant complainant = complainantRepo.getById(nextOfKin.getNationalId());
        complainant.getNextOfKin().getNationalId();
        return complainantRepo.save(complainant);
    }

    @Override
    public void deleteComplainantById(String nationalId) {
        complainantRepo.deleteById(nationalId);

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
