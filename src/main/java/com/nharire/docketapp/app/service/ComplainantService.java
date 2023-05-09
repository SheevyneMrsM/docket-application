package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.NextOfKin;
import com.nharire.docketapp.app.model.dto.ComplainantDTO;

import java.util.List;
import java.util.Optional;

public interface ComplainantService {

    ComplainantDTO saveComplainantDetails(ComplainantDTO complainantDTO);

    ComplainantDTO updateComplainantDetails(ComplainantDTO complainantDTO);

    ComplainantDTO addNextOfKin(NextOfKin nextOfKin);

    void deleteComplainantById(String nationalId);

    Optional<ComplainantDTO> getComplainantDetails(String nationalId);


    List<ComplainantDTO> getAllComplainantDetails();

    List<ComplainantDTO> getDateOfComplaint(String dateReported);

}
