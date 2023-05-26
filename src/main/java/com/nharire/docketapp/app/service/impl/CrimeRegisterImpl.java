package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Accused;
import com.nharire.docketapp.app.model.Complainant;
import com.nharire.docketapp.app.model.CrimeRegister;
import com.nharire.docketapp.app.model.dto.CrimeRegisterDTO;
import com.nharire.docketapp.app.repository.AccusedRepo;
import com.nharire.docketapp.app.repository.CrimeRegisterRepo;
import com.nharire.docketapp.app.service.CrimeRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class CrimeRegisterImpl implements CrimeRegisterService {
    private final CrimeRegisterRepo crimeRegisterRepo;
    private final AccusedRepo accusedRepo;
    @Override
    public CrimeRegister saveCrimeRegisterDetails(CrimeRegisterDTO crimeRegisterDTO) {
        log.info("SAVE CRIME REGISTER DETAILS: {}", crimeRegisterDTO);
        CrimeRegister crimeRegister = new CrimeRegister();
        BeanUtils.copyProperties(crimeRegisterDTO,crimeRegister);
        log.info("Saving crime register details: {}",crimeRegister);
        return crimeRegisterRepo.save(crimeRegister);
    }

    @Override
    public CrimeRegisterDTO updateCrimeRegisterDetails(CrimeRegisterDTO crimeRegisterDTO) {
        return null;
    }

    @Override
    public void deleteCrimeRegisterById(Long crimeId) {

    }

    @Override
    public void deleteAccusedDetailsById(String nationalId) {

    }

    @Override
    public void deleteComplainantDetailsById(String nationalId) {

    }

    @Override
    public CrimeRegister addAccused(Accused accused) {
        CrimeRegister crimeRegister= crimeRegisterRepo.getById(accused.getCrime().getCrimeId());
        crimeRegister.getAccusedList().add(accused);
        return crimeRegisterRepo.save(crimeRegister);
    }

    @Override
    public CrimeRegisterDTO addComplainant(Complainant complainant) {
        return null;
    }

    @Override
    public List<CrimeRegister> getAllCrimeRegisterDetails() {
        return null;
    }

    @Override
    public Optional<CrimeRegister> getCrimeRegisterDetails(Long crimeId) {
        return Optional.empty();
    }

    @Override
    public List<CrimeRegister> getAllByDateOfReport(Date dateOfReport) {
        return crimeRegisterRepo.getAllByDateOfReport(dateOfReport);
    }
}
