package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.Officer;
import com.nharire.docketapp.app.model.dto.OfficerDTO;
import com.nharire.docketapp.app.model.dto.response.OfficerResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OfficerService {

    OfficerResponse saveOfficerDetails(OfficerDTO officerDTO);

    OfficerResponse updateOfficerDetails(OfficerDTO officerDTO);

    void deleteOfficerById(String nationalId);

    List<Officer> getAllOfficers();

    Optional<Officer> getOfficerDetails(String nationalId);




}
