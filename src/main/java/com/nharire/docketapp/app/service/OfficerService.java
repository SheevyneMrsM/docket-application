package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.Officer;
import com.nharire.docketapp.app.model.dto.OfficerDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OfficerService {

    Officer saveOfficerDetails(OfficerDTO officerDTO);

    OfficerDTO updateOfficerDetails(OfficerDTO officerDTO);

    void deleteOfficerById(Long id);

    List<Officer> getAllOfficers();

    Optional<Officer> getOfficerDetails(Long id);




}
