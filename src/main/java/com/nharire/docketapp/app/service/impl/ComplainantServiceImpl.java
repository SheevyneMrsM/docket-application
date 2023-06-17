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
