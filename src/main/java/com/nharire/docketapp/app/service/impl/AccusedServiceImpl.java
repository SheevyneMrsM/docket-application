package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Accused;
import com.nharire.docketapp.app.model.NextOfKin;
import com.nharire.docketapp.app.model.dto.AccusedDTO;
import com.nharire.docketapp.app.repository.AccusedRepo;
import com.nharire.docketapp.app.service.AccusedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccusedServiceImpl implements AccusedService {


    private final AccusedRepo accusedRepo;

    @Override
    public Accused saveAccusedDetails(AccusedDTO accusedDTO) {
        log.info("ACCUSED DETAILS: {}", accusedDTO.toString());
        Accused accused = new Accused();
        BeanUtils.copyProperties(accusedDTO,accused);
        log.info("Saving new accused details: {}", accused);
        return accusedRepo.save(accused);
    }


    @Override
    public Optional<Accused> getAccusedDetails(String nationalId) {
        return  accusedRepo.findById(nationalId);

    }

    @Override
    public List<Accused> getAllAccusedDetails() {
        return accusedRepo.findAll();
    }

    @Override
    public List<AccusedDTO> getAccusedByDateReported(String dateReported) {
        return null;
    }

    @Override
    public void deleteById(String nationalId) {
        accusedRepo.deleteById(nationalId);

    }

    @Override
    public AccusedDTO updateAccusedDetails(AccusedDTO accusedDTO) {
        return null;
    }

    @Override
    public Accused addNextOfKin(NextOfKin nextOfKin) {
        Accused accused = accusedRepo.getById(nextOfKin.getAccused().getNationalId());
        accused.getNextOfKin().add(nextOfKin);
        return accusedRepo.save(accused);
    }
}
