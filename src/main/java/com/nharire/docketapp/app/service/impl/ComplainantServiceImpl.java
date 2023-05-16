package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Complainant;
import com.nharire.docketapp.app.model.NextOfKin;
import com.nharire.docketapp.app.model.Witness;
import com.nharire.docketapp.app.model.dto.ComplainantDTO;
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
    @Override
    public Complainant saveComplainantDetails(ComplainantDTO complainantDTO) {
        log.info("SAVE COMPLAINANT DETAILS: {}",complainantDTO.toString());
        Complainant complainant = new Complainant();
        BeanUtils.copyProperties(complainantDTO,complainant);
        log.info("Saving complainant details: {}", complainant);
        return complainantRepo.save(complainant);

    }

    @Override
    public ComplainantDTO updateComplainantDetails(ComplainantDTO complainantDTO) {
        return null;
    }

    @Override
    public ComplainantDTO addNextOfKin(NextOfKin nextOfKin) {
        return null;
    }

    @Override
    public void deleteComplainantById(String nationalId) {

    }

    @Override
    public Optional<ComplainantDTO> getComplainantDetails(String nationalId) {
        return Optional.empty();
    }

    @Override
    public List<ComplainantDTO> getAllComplainantDetails() {
        return null;
    }

    @Override
    public List<ComplainantDTO> getDateOfComplaint(String dateReported) {
        return null;
    }

    @Override
    public ComplainantDTO addWitnessDetails(Witness witness) {
        return null;
    }
}
