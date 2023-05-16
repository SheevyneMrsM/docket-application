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
        BeanUtils.copyProperties(officerDTO,officer);
        log.info("Saving officer details: {}", officer);
        return officerRepo.save(officer);
    }

    @Override
    public OfficerDTO updateOfficerDetails(OfficerDTO officerDTO) {
        return null;
    }

    @Override
    public void deleteOfficerById(Long id) {

    }

    @Override
    public List<OfficerDTO> getAllOfficers() {
        return null;
    }

    @Override
    public Optional<OfficerDTO> getOfficerDetails(Long id) {
        return Optional.empty();
    }

    @Override
    public OfficerDTO addOfficerDetails(Officer officer) {
        return null;
    }
}
