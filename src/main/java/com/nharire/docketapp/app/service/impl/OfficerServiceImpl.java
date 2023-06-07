package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Officer;
import com.nharire.docketapp.app.model.dto.OfficerDTO;
import com.nharire.docketapp.app.repository.OfficerRepo;
import com.nharire.docketapp.app.service.OfficerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class OfficerServiceImpl implements OfficerService {

    private final OfficerRepo officerRepo;

    @Override
    public Officer saveOfficerDetails(OfficerDTO officerDTO) {
        log.info("SAVE OFFICER DETAILS: {}", officerDTO.toString());
        Officer officer = new Officer();
        BeanUtils.copyProperties(officerDTO, officer);
        log.info("Saving officer details: {}", officer);
        return officerRepo.save(officer);
    }

    @Override
    public OfficerDTO updateOfficerDetails(OfficerDTO officerDTO) {
        Optional<Officer> officer = officerRepo.findById(officerDTO.getId());
        Officer officer1;
        if (officer.isPresent()) {
            officer1 = officer.get();
            BeanUtils.copyProperties(officerDTO, officer1);
        } else {
            throw new RuntimeException("No details found, cant update!!!");
        }
        BeanUtils.copyProperties(officer1, officerDTO);
        return officerDTO;
    }

    @Override
    public void deleteOfficerById(Long id) {
        officerRepo.deleteById(id);

    }

    @Override
    public List<Officer> getAllOfficers() {

        return officerRepo.findAll();
    }

    @Override
    public Optional<Officer> getOfficerDetails(Long id) {

        return officerRepo.findById(id);
    }

}
