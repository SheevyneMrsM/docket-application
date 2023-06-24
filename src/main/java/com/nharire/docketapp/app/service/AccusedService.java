package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.Accused;
import com.nharire.docketapp.app.model.NextOfKin;
import com.nharire.docketapp.app.model.dto.AccusedDTO;
import com.nharire.docketapp.app.model.dto.response.AccusedResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AccusedService {

     Accused saveAccusedDetails(AccusedDTO accusedDTO);
     Optional<Accused> getAccusedDetails(String nationalId);

     List<Accused> getAllAccusedDetails();

     List<AccusedDTO> getAccusedByDateReported(String dateReported);

     void deleteById(String nationalId);

    AccusedResponse updateAccusedDetails(AccusedDTO accusedDTO);

    Accused addNextOfKin(NextOfKin nextOfKin);




}
