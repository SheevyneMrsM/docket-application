package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.Complainant;
import com.nharire.docketapp.app.model.NextOfKin;
import com.nharire.docketapp.app.model.Witness;
import com.nharire.docketapp.app.model.dto.ComplainantDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface ComplainantService {

    Complainant saveComplainantDetails(ComplainantDTO complainantDTO);

    ComplainantDTO updateComplainantDetails(ComplainantDTO complainantDTO);

    Complainant addNextOfKin(NextOfKin nextOfKin);

    void deleteComplainantById(String nationalId);

    Optional<Complainant> getComplainantDetails(String nationalId);


    List<Complainant> getAllComplainantDetails();

    List<Complainant> getDateOfComplaint(String dateReported);

    Complainant addWitnessDetails(Witness witness);

}
